package com.mpowloka.data.localizations

import com.mpowloka.domain.localizations.Localization
import com.mpowloka.domain.localizations.LocalizationsRepository
import io.reactivex.Flowable
import javax.inject.Inject

class LocalizationsRepositoryImpl @Inject constructor() : LocalizationsRepository {

    override fun getAllLocalizations(): Flowable<List<Localization>> {

        return Flowable.just(
            listOf(
                Localization(1, "Loc1", "457-AGH", 5, 2),
                Localization(2, "Loc2", "457-CSR", 0, 0),
                Localization(3, "Loc3", "218-HGW", 3, 3)
            )
        )

    }

}