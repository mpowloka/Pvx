package com.mpowloka.pvx.screens.localizationdetails

import com.mpowloka.domain.items.Item
import com.mpowloka.domain.items.ItemWithTotalCount
import com.mpowloka.domain.items.ItemsRepository
import com.mpowloka.domain.localizations.Localization
import com.mpowloka.domain.localizations.LocalizationsRepository
import com.mpowloka.pvx.screens.localizationdetails.list.LocalizationDetailsAdapterItem
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test

class LocalizationDetailsViewModelTest {

    private lateinit var SUT: LocalizationDetailsViewModel

    private lateinit var itemsRepositoryMock: ItemsRepository
    private lateinit var localizationsRepositoryMock: LocalizationsRepository

    @Before
    fun setUp() {
        mockRepositories()
        SUT = LocalizationDetailsViewModel(
            itemsRepositoryMock,
            localizationsRepositoryMock
        )
    }

    @Test
    fun getAdapterItemsForLocalization_localizationPassedToRepository() {

        SUT.getAdapterItemsForLocalization(LOCALIZATION_ID)

        verify(itemsRepositoryMock, times(1)).getItemsInLocalizationWithTotalCount(LOCALIZATION_ID)
    }

    @Test
    fun getAdapterItemsForLocalization_valueFromRepositoryReturned() {

        val expectedResult = ITEMS_WITH_TOTAL_COUNT_FROM_REPOSITORY.map {
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

        verify(itemsRepositoryMock, times(1)).getItemsInLocalizationWithTotalCount(LOCALIZATION_ID)
        verifyNoMoreInteractions(itemsRepositoryMock)
    }

    @Test
    fun getLocalizationForId_localizationPassedToRepository() {

        SUT.getLocalizationForId(LOCALIZATION_ID)

        verify(localizationsRepositoryMock, times(1)).getLocalizationForId(LOCALIZATION_ID)
    }

    @Test
    fun getLocalizationForId_valueFromRepositoryReturned() {

        val result = SUT.getLocalizationForId(LOCALIZATION_ID)

        result.test().assertValue(
            LOCALIZATION
        )

    }

    @Test
    fun getLocalizationForId_calledMultipleTimesWithSameId_RepositoryQueriedOnlyOnce() {

        SUT.getLocalizationForId(LOCALIZATION_ID)
        SUT.getLocalizationForId(LOCALIZATION_ID)

        verify(localizationsRepositoryMock, times(1)).getLocalizationForId(LOCALIZATION_ID)
        verifyNoMoreInteractions(itemsRepositoryMock)
    }

    @Test
    fun getAdapterItemsForLocalization_calledMultipleTimesWithSameId_RepositoryQueriedOnlyOnce_noItems_noDataItemReturned() {
        mockNoItems()

        SUT.getAdapterItemsForLocalization(LOCALIZATION_ID).test().assertValue {
            it.size == 1 && it[0] is LocalizationDetailsAdapterItem.NoDataItem
        }
    }

    private fun mockNoItems() {
        whenever(itemsRepositoryMock.getItemsInLocalizationWithTotalCount(LOCALIZATION_ID)).thenReturn(
            Flowable.just(emptyList())
        )
    }

    private fun mockRepositories() {
        itemsRepositoryMock = mock()
        whenever(itemsRepositoryMock.getItemsInLocalizationWithTotalCount(LOCALIZATION_ID)).thenReturn(
            Flowable.just(ITEMS_WITH_TOTAL_COUNT_FROM_REPOSITORY)
        )

        localizationsRepositoryMock = mock()
        whenever(localizationsRepositoryMock.getLocalizationForId(LOCALIZATION_ID)).thenReturn(
            Flowable.just(LOCALIZATION)
        )
    }

    companion object {

        private val LOCALIZATION_ID = 1L

        private val LOCALIZATION = Localization(1, "Loc1", "457-AGH", 5, 2)

        private val ITEMS_WITH_TOTAL_COUNT_FROM_REPOSITORY = listOf(
            ItemWithTotalCount(Item(1, "Item1", "1-ASDF-GAT"), 5),
            ItemWithTotalCount((Item(2, "Item2", "2-ASDF-GAT")), 0),
            ItemWithTotalCount(Item(3, "Item3", "3-ASDF-GAT"), 15)
        )

    }
}