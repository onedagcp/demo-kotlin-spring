package com.example.demo.service.impl

import com.example.demo.entity.Memo
import com.example.demo.repository.MemoRepository
import com.example.demo.service.MemoService
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemoServiceImpl(
        private val repository: MemoRepository) : MemoService {

    override fun findById2(id: Long): Memo? =
        repository.findOne(id)

    @Transactional(readOnly = true)
    override fun findById(id: Long): Memo? =
            repository.findOne(id)

    @Transactional(readOnly = true)
    override fun findAll(page: Pageable): List<Memo> =
            repository.findAll(page).content

    @Transactional(timeout = 10)
    override fun store(memo: Memo) {
        repository.save(memo)
    }

    @Transactional(timeout = 10)
    override fun updateById(id: Long, memo: Memo): Memo? {
        val targetMemo = repository.findOne(id)
        targetMemo?.let { it.merge(memo) }
        return targetMemo
    }

    @Transactional(timeout = 10)
    override fun removeById(id: Long) {
        repository.delete(id)
    }

}