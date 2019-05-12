package com.mpowloka.pvx.screens.itemdetails

import com.mpowloka.domain.items.Item
import com.mpowloka.domain.items.ItemsRepository
import com.mpowloka.domain.localizations.Localization
import com.mpowloka.domain.localizations.LocalizationsRepository
import com.mpowloka.pvx.screens.itemdetails.list.ItemDetailsAdapterItem
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test

class ItemDetailsViewModelTest {

    private lateinit var SUT: ItemDetailsViewModel

    private lateinit var localizationsRepositoryMock: LocalizationsRepository
    private lateinit var itemsRepositoryMock: ItemsRepository

    @Before
    fun setUp() {
        mockRepositories()
        SUT = ItemDetailsViewModel(
            localizationsRepositoryMock,
            itemsRepositoryMock
        )
    }

    @Test
    fun getAdapterItemsForItem_calledMultipleTimesWithSameId_RepositoryQueriedOnlyOnce() {

        SUT.getAdapterItemsForItem(ITEM_ID)
        SUT.getAdapterItemsForItem(ITEM_ID)

        verify(localizationsRepositoryMock, times(1)).getLocalizationsWithItem(ITEM_ID)
        verifyNoMoreInteractions(localizationsRepositoryMock)

    }

    @Test
    fun getAdapterItemsForItem_itemIdPassedToRepository() {
        SUT.getAdapterItemsForItem(ITEM_ID)

        verify(localizationsRepositoryMock, times(1)).getLocalizationsWithItem(ITEM_ID)
    }

    @Test
    fun getAdapterItemsForItem_itemsFromRepositoryReturned() {
        val expectedResult = LOCALIZATIONS_FROM_REPOSITORY.map {
            ItemDetailsAdapterItem.ItemLocalization(it)
        }

        val result = SUT.getAdapterItemsForItem(ITEM_ID)

        result.test().assertValue(expectedResult)
    }

    @Test
    fun getItemForItemId_calledMultipleTimesWithSameId_RepositoryQueriedOnlyOnce() {

        SUT.getItemForItemId(ITEM_ID)
        SUT.getItemForItemId(ITEM_ID)

        verify(itemsRepositoryMock, times(1)).getItemForId(ITEM_ID)
        verifyNoMoreInteractions(itemsRepositoryMock)

    }

    @Test
    fun getItemForItemId_itemIdPassedToRepository() {
        SUT.getItemForItemId(ITEM_ID)

        verify(itemsRepositoryMock, times(1)).getItemForId(ITEM_ID)
    }

    @Test
    fun getItemForItemId_itemFromRepositoryReturned() {
        val result = SUT.getItemForItemId(ITEM_ID)

        result.test().assertValue(ITEM)
    }

    @Test
    fun getAdapterItemsForItem_noLocalizations_noDataItemReturned() {
        mockNoLocalizations()

        SUT.getAdapterItemsForItem(ITEM_ID).test().assertValue {
            it.size == 1 && it[0] is ItemDetailsAdapterItem.NoDataItem
        }
    }

    private fun mockNoLocalizations() {
        whenever(localizationsRepositoryMock.getLocalizationsWithItem(ITEM_ID)).thenReturn(
            Flowable.just(emptyList())
        )
    }

    private fun mockRepositories() {
        localizationsRepositoryMock = mock()
        whenever(localizationsRepositoryMock.getLocalizationsWithItem(ITEM_ID)).thenReturn(
            Flowable.just(LOCALIZATIONS_FROM_REPOSITORY)
        )

        itemsRepositoryMock = mock()
        whenever(itemsRepositoryMock.getItemForId(ITEM_ID)).thenReturn(
            Flowable.just(ITEM)
        )
    }

    companion object {

        private const val ITEM_ID = 42L

        private val ITEM = Item(1, "Item1", "1-ASDF-GAT")

        private val LOCALIZATIONS_FROM_REPOSITORY = listOf(
            Localization(1, "Loc1", "457-AGH", 5, 2),
            Localization(2, "Loc2", "457-CSR", 0, 0),
            Localization(3, "Loc3", "218-HGW", 3, 3)
        )

    }

}