package com.example.demo.service.impl

import com.example.demo.entity.Memo
import com.example.demo.repository.MemoRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest

/**
 * 単体テスト
 */
class MemoServiceImplTests {

    @get:Rule
    val rule = MockitoJUnit.rule()

    @Mock
    lateinit var repository: MemoRepository
    @InjectMocks
    lateinit var sut: MemoServiceImpl

    @Test
    fun findById() {
        val expected = Memo(id = 1, title = "title", description = "description", done = true)
        Mockito.`when`(repository.findOne(Mockito.anyLong())).thenReturn(expected)

        val actual = sut.findById(expected.id)

        assertThat(actual).`as`("actualは必ず検索できる").isNotNull()
        actual?.let {
            assertThat(it).isEqualTo(expected)
        }
    }

    @Test
    fun findAll() {
        val page = PageRequest(0, 5)
        val expected = listOf(
                Memo(id = 1, title = "test title 1", description = "test description 1"),
                Memo(id = 2, title = "test title 2", description = "test description 2"),
                Memo(id = 3, title = "test title 3", description = "test description 3")
        )
        Mockito.`when`(repository.findAll(Mockito.eq(page))).thenReturn(PageImpl(expected, page, 3))

        val actual = sut.findAll(page)

        assertThat(actual).hasSize(3)
    }

}