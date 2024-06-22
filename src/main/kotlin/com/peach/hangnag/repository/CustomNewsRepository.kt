package com.peach.hangnag.repository

import com.peach.hangnag.entity.vo.IssueVo

interface CustomNewsRepository {
    fun findAllIssueByViewCount(): List<IssueVo>
    fun findAllIssueByCreateDate(): List<IssueVo>
}
