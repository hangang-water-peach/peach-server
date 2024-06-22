package com.peach.hangnag.service

import com.peach.hangnag.common.feign.client.NewsClient
import com.peach.hangnag.common.feign.dto.response.NewsResponse
import com.peach.hangnag.entity.News
import com.peach.hangnag.repository.NewsRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.net.URLEncoder
import java.nio.charset.Charset
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Service
class NewsCronService(
    private val newsClient: NewsClient,
    private val newsRepository: NewsRepository,
    @Value("\${naver.client-id}")
    private val naverClientId: String,
    @Value("\${naver.client-secret}")
    private val naverClientSecret: String,
) {
    fun newsCron(queryKey: String): NewsResponse {
        val user = Any()
        val items = newsClient.getNewsBySearchKey(
            queryKey = URLEncoder.encode(queryKey, Charset.forName("UTF-8")),
            clientId = naverClientId,
            clientSecret = naverClientSecret,
        ).items

        val newsList = items.map { news ->
            News(
                title = news.title,
                content = news.description,
                createDate = dateParser(news.pubDate),
                link = news.originallink,
            )
        }
        newsRepository.saveAll(newsList)

        return NewsResponse(items)
    }

    private fun dateParser(target: String): LocalDate {
        val subStringDate = target.substring(5, 16)
        val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH)
        return LocalDate.parse(subStringDate, formatter)
    }
}
