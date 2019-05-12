package com.mpowloka.pvx.screens.localizations

import com.mpowloka.domain.localizations.Localization
import com.mpowloka.domain.localizations.LocalizationsRepository
import com.mpowloka.pvx.screens.localizations.list.LocalizationsAdapterItem
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test

class LocalizationsViewModelTest {

    private lateinit var SUT: LocalizationsViewModel

    private lateinit var localizationsRepositoryMock: LocalizationsRepository

    @Before
    fun setUp() {
        mockRepository()
        SUT = LocalizationsViewModel(localizationsRepositoryMock)
    }

    @Test
    fun adapterItems_holdsItemsWithLocalizationsFromRepository() {

        val expectedItems = LOCALIZATIONS_FROM_REPOSITORY.map {
            LocalizationsAdapterItem.LocalizationItem(it)
        }

        SUT.localizations.test().assertValue(
            expectedItems
        )
    }

    private fun mockRepository() {
        localizationsRepositoryMock = mock()
        whenever(localizationsRepositoryMock.getAllLocalizations()).thenReturn(
            Flowable.just(LOCALIZATIONS_FROM_REPOSITORY)
        )
    }

    companion object {
        private val LOCALIZATIONS_FROM_REPOSITORY = listOf(
            Localization(1, "Loc1", "457-AGH", 5, 2),
            Localization(2, "Loc2", "457-CSR", 0, 0),
            Localization(3, "Loc3", "218-HGW", 3, 3)
        )
    }
}