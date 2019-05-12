package com.mpowloka.pvx.itemdetails

import com.mpowloka.domain.localizations.Localization
import com.mpowloka.domain.localizations.LocalizationsRepository
import com.mpowloka.pvx.itemdetails.list.ItemDetailsAdapterItem
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test

class ItemDetailsViewModelTest {

    private lateinit var SUT: ItemDetailsViewModel

    private lateinit var localizationsRepositoryMock: LocalizationsRepository

    @Before
    fun setUp() {
        mockRepository()
        SUT = ItemDetailsViewModel(localizationsRepositoryMock)
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

    private fun mockRepository() {
        localizationsRepositoryMock = mock()
        whenever(localizationsRepositoryMock.getLocalizationsWithItem(ITEM_ID)).thenReturn(
            Flowable.just(LOCALIZATIONS_FROM_REPOSITORY)
        )
    }

    companion object {

        private const val ITEM_ID = 42L

        private val LOCALIZATIONS_FROM_REPOSITORY = listOf(
            Localization(1, "Loc1", "457-AGH", 5, 2),
            Localization(2, "Loc2", "457-CSR", 0, 0),
            Localization(3, "Loc3", "218-HGW", 3, 3)
        )

    }

}