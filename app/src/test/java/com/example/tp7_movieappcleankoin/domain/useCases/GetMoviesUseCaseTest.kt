package com.example.tp7_movieappcleankoin.domain.useCases

import com.example.tp7_movieappcleankoin.data.service.model.Movie
import com.example.tp7_movieappcleankoin.domain.repository.MoviesRepository
import com.example.tp7_movieappcleankoin.presentation.viewModel.CoroutinesTestRule
import com.example.tp7_movieappcleankoin.domain.util.CoroutineResult
import io.mockk.mockkClass
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class GetMoviesUseCaseTest{
    @get:Rule
    val coroutineTestRule = CoroutinesTestRule()

    private val repositoryMock= mockkClass(MoviesRepository::class)
    val useCase: GetMoviesUseCase = GetMoviesUseCase(repositoryMock)
    private val movieMock= mockkClass(Movie::class)


    @Test
    fun `when service called with success`() = coroutineTestRule.testDispatcher.runBlockingTest {

        val result = useCase.getMovies()
        val lista: List<Movie> = listOf(movieMock)
        Assert.assertEquals(result, CoroutineResult.Success<List<Movie>>(lista))
    }
}