package com.peach.hangnag.repository

import com.peach.hangnag.entity.News
import org.springframework.data.repository.CrudRepository

interface NewsRepository : CrudRepository<News, Long>, CustomNewsRepository