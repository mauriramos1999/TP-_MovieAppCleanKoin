package com.example.tp7_movieappcleankoin.presentation.viewModel

import com.example.tp7_movieappcleankoin.domain.repository.MoviesRepository
import com.example.tp7_movieappcleankoin.domain.useCases.GetMoviesUseCase
import io.mockk.mockkClass
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ListViewModelTest {

    @get:Rule
    //var rule: TestRule = InstantTaskExecutorRule()
    //private val testDispatcher = TestCoroutineDispatcher()
    val coroutineTestRule = CoroutinesTestRule()

    private lateinit var viewModel: ListViewModel
    private val repositoryMock= mockkClass(MoviesRepository::class)
    val useCase: GetMoviesUseCase = GetMoviesUseCase(repositoryMock)

    @Before
    fun setup(){
      //  Dispatchers.setMain(testDispatcher)
        viewModel = ListViewModel(useCase)
    }

    @Test
    fun initialStateTest(){
        assert(viewModel.getValue().value == null)
    }

    @Test
    fun `when service called with success`() = coroutineTestRule.testDispatcher.runBlockingTest{
        viewModel.callService()
        Assert.assertEquals(viewModel.getValue().value?.mainStatus, ListViewModel.MainStatus.SHOW_INFO)

    }

}