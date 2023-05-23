package com.example.tp7_movieappcleankoin.presentation.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.tp7_movieappcleankoin.data.service.model.Movie
import com.example.tp7_movieappcleankoin.domain.repository.MoviesRepository
import com.example.tp7_movieappcleankoin.domain.useCases.GetMoviesUseCase
import com.example.tp7_movieappcleankoin.util.CoroutineResult
import io.mockk.mockkClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.koin.java.KoinJavaComponent.inject

class MainViewModelTest {

    @get:Rule
    //var rule: TestRule = InstantTaskExecutorRule()
    //private val testDispatcher = TestCoroutineDispatcher()
    val coroutineTestRule = CoroutinesTestRule()

    private lateinit var viewModel: MainViewModel
    private val repositoryMock= mockkClass(MoviesRepository::class)
    val useCase: GetMoviesUseCase = GetMoviesUseCase(repositoryMock)

    @Before
    fun setup(){
      //  Dispatchers.setMain(testDispatcher)
        viewModel = MainViewModel(useCase)
    }

    @Test
    fun initialStateTest(){
        assert(viewModel.getValue().value == null)
    }

    @Test
    fun `when service called with success`() = coroutineTestRule.testDispatcher.runBlockingTest{
        viewModel.callService()
        Assert.assertEquals(viewModel.getValue().value?.mainStatus, MainViewModel.MainStatus.SHOW_INFO)

    }

}