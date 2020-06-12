package it.wemake.covid19Companion.domain.usecases

import it.wemake.covid19Companion.domain.repository.ICountriesRepository
import it.wemake.covid19Companion.domain.repository.IDevelopmentTeamRepository
import javax.inject.Inject

class GetDevelopmentTeamMembersUseCase
@Inject constructor(
    private val developmentTeamRepository: IDevelopmentTeamRepository
) {

    suspend operator fun invoke() = developmentTeamRepository.getTeamMembers()

}