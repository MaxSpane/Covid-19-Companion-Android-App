package it.weMake.covid19Companion.ui.screeningTool

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import it.weMake.covid19Companion.commons.Loading
import it.weMake.covid19Companion.commons.Success
import it.weMake.covid19Companion.commons.UiStateViewModel
import it.weMake.covid19Companion.mappers.toDomain
import it.weMake.covid19Companion.mappers.toPresentation
import it.weMake.covid19Companion.models.screeningTool.Diagnosis
import it.weMake.covid19Companion.models.screeningTool.NextQuestion
import it.weMake.covid19Companion.models.screeningTool.Question
import it.weMake.covid19Companion.models.screeningTool.request.Evidence
import it.wemake.covid19Companion.domain.models.screeningTool.requests.ScreeningToolRequestDomainModel
import it.wemake.covid19Companion.domain.usecases.GetDiagnosisUseCase
import it.wemake.covid19Companion.domain.usecases.RequestNextQuestionUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ScreeningToolViewModel @Inject constructor(
    private val requestNextQuestionUseCase: RequestNextQuestionUseCase,
    private val getDiagnosisUseCase: GetDiagnosisUseCase
): UiStateViewModel() {

    private val _nextQuestionLiveData = MutableLiveData<NextQuestion>()
    val nextQuestionLiveData: LiveData<NextQuestion>
        get() = _nextQuestionLiveData

    private val _diagnosisLiveData = MutableLiveData<Diagnosis>()
    val diagnosisLiveData: LiveData<Diagnosis>
        get() = _diagnosisLiveData

    private val questions = ArrayList<Question>()
    private val answers = ArrayList<List<Evidence>>()
    private var questionNumber = -1
    private var gender: String? = null
    private var age: Int? = null

    private val _showBackgroundLiveData = MutableLiveData(false)
    val showBackgroundLiveData: LiveData<Boolean>
        get() = _showBackgroundLiveData
    private val _loadingTextLiveData = MutableLiveData<String>()
    val loadingTextLiveData: LiveData<String>
        get() = _loadingTextLiveData

    fun nextQuestion(newEvidence: List<Evidence>){

        if (questionNumber == questions.size - 1) {
            requestNextQuestion(newEvidence)
        } else {
            if (answers[questionNumber] == newEvidence){
                _nextQuestionLiveData.value = NextQuestion(false, questions[++questionNumber])
            }else{
                questions.subList(questionNumber + 1, questions.size).clear()
                answers.subList(questionNumber, answers.size).clear()
                requestNextQuestion(newEvidence)
            }
        }

    }

    //Loads the previous Question and returns whether or not the user is on the first question
    fun previousQuestion(): Boolean =
        if (questionNumber == 0){
            true
        }else{
            _nextQuestionLiveData.value = NextQuestion(false, questions[--questionNumber])
            false
        }

    //Requests the first question and returns whether or not the user has already requested the first question with the same age and gender
    fun requestFirstQuestion(gender: String, age: Int): Boolean =
        if (this.gender == gender && this.age == age && questionNumber != -1){
//            _nextQuestionLiveData.value = NextQuestion(false, questions[questionNumber])
            true
        }else{
            if(questionNumber != -1)
                questionNumber = -1
            this.gender = gender
            this.age = age
            requestNextQuestion(listOf())
            false
        }

    private fun requestNextQuestion(newEvidence: List<Evidence>){
        _uiState.value = Loading

        viewModelScope.launch(handler) {
            requestNextQuestionUseCase(
                ScreeningToolRequestDomainModel(
                    gender!!,
                    age!!,
                    compileEvidence(newEvidence).map { it.toDomain() }
                )
            ).collect {
                val nextQuestion = it.toPresentation()

                questionNumber++
                if (!nextQuestion.shouldStop) {
                    questions.add(nextQuestion.question!!)
                }

                if(newEvidence.isNotEmpty())
                    answers.add(newEvidence)

                _nextQuestionLiveData.value = nextQuestion
                _uiState.value = Success
            }
        }
    }

    private fun compileEvidence(newEvidence: List<Evidence>? = null): List<Evidence> {
        val evidence = ArrayList<Evidence>()

        for (i in 0 until questionNumber){
            evidence.addAll(answers[i])
        }

        newEvidence?.let {
            evidence.addAll(it)
        }
        return evidence
    }

    fun setShowBackground(showBackground: Boolean){
        _showBackgroundLiveData.value = showBackground
    }

    fun setLoadingText(loadingText: String){
        _loadingTextLiveData.value = loadingText
    }

    fun getDiagnosis(){
        _uiState.value = Loading

        viewModelScope.launch(handler) {
            getDiagnosisUseCase(
                ScreeningToolRequestDomainModel(
                    gender!!,
                    age!!,
                    compileEvidence().map { it.toDomain() }
                )
            ).collect {
                val diagnosis = it.toPresentation()

                _diagnosisLiveData.value = diagnosis
                _uiState.value = Success
            }
        }
    }

}