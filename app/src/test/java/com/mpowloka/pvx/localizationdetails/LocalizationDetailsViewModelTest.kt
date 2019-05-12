package com.mpowloka.pvx.localizationdetails

import com.mpowloka.domain.items.Item
import com.mpowloka.domain.items.ItemsRepository
import com.mpowloka.pvx.localizationdetails.list.LocalizationDetailsAdapterItem
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test

class LocalizationDetailsViewModelTest {

    private lateinit var SUT: LocalizationDetailsViewModel

    private lateinit var itemsRepositoryMock: ItemsRepository

    @Before
    fun setUp() {
        mockRepository()
        SUT = LocalizationDetailsViewModel(
            itemsRepositoryMock
        )
    }

    @Test
    fun getAdapterItemsForLocalization_localizationPassedToRepository() {

        SUT.getAdapterItemsForLocalization(LOCALIZATION_ID)

        verify(itemsRepositoryMock, times(1)).getItemsInLocalization(LOCALIZATION_ID)
    }

    @Test
    fun getAdapterItemsForLocalization_valueFromRepositoryReturned() {

        val expectedResult = ITEMS_FROM_REPOSITORY.map {
            LocalizationDetailsAdapterItem.ItemItem(it)
        }

        val result = SUT.getAdapterItemsForLocalization(LOCALIZATION_ID)

        result.test().assertValue(
            expectedResult
        )

    }

    @Test
    fun getAdapterItemsForLocalization_calledMultipleTimesWithSameId_RepositoryQueriedOnlyOnce() {

        SUT.getAdapterItemsForLocalization(LOCALIZATION_ID)
        SUT.getAdapterItemsForLocalization(LOCALIZATION_ID)

        verify(itemsRepositoryMock, times(1)).getItemsInLocalization(LOCALIZATION_ID)
        verifyNoMoreInteractions(itemsRepositoryMock)
    }

    private fun mockRepository() {
        itemsRepositoryMock = mock()
        whenever(itemsRepositoryMock.getItemsInLocalization(LOCALIZATION_ID)).thenReturn(
            Flowable.just(ITEMS_FROM_REPOSITORY)
        )
    }

    companion object {

        private val LOCALIZATION_ID = 1L

        private val ITEMS_FROM_REPOSITORY = listOf(
            Item(1, "Item1", "1-ASDF-GAT"),
            Item(2, "Item2", "2-ASDF-GAT"),
            Item(3, "Item3", "3-ASDF-GAT")
        )
    }
}