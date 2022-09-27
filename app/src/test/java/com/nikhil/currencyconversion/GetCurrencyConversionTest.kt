package com.nikhil.currencyconversion

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.nikhil.currencyconversion.data.network.CurrencyService
import com.nikhil.currencyconversion.data.repository.CurrencyRepo
import com.nikhil.currencyconversion.domain.repository.CurrencyRepository
import com.nikhil.currencyconversion.domain.use_cases.GetCurrencySymbol
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.mock
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import util.MockResponseFileReader
import javax.inject.Inject

class GetCurrencyConversionTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()



//    val coinMarketCapRepository = mock<CurrencyRepository>()
//    val cryptoListUseCases by lazy {
//        GetCurrencySymbol(coinMarketCapRepository)
//    }

    @Test
    fun testCryptoListUseCases_getCryptoList_Completed() {
//        whenever(coinMarketCapRepository.getCryptoList(
//            anyInt(), anyInt()))
//            .thenReturn(Single.just(emptyList()))
//
//        cryptoListUseCases.getCryptoListBy(0)
//            .test()
//            .assertComplete()
    }
//    @Before
//    fun setUp() {
//        validatePassword = GetCurrencySymbol(repository)
//    }
//    @get:Rule
//    val instantExecutorRule = InstantTaskExecutorRule()
//
//
//    private val server = MockWebServer()
//  @Mock
//  private lateinit var repository: CurrencyRepo
//    private lateinit var mockedResponse: String
//
//    private val gson = GsonBuilder()
//        .setLenient()
//        .create()
//
//
//    @Before
//    fun init() {
//        server.start(8000)
//
//        var BASE_URL = server.url("/").toString()
//
//        val okHttpClient = OkHttpClient
//            .Builder()
//            .build()
//        val service = Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl(BASE_URL)
//            .client(okHttpClient)
//            .build().create(CurrencyService::class.java)
//
//        repository = CurrencyRepo(service)
//    }
//    @Test
//    fun testApiSuccess() {
//        mockedResponse = MockResponseFileReader("currency/symbol_success.json").content
//
//        server.enqueue(
//            MockResponse()
//                .setResponseCode(200)
//                .setBody(mockedResponse)
//        )
//
//        val response = runBlocking { repository.fetchSymbol() }
//        val json = gson.toJson(response.body())
//
//        val resultResponse = JsonParser.parseString(json)
//        val expectedresponse = JsonParser.parseString(mockedResponse)
//
//        Assert.assertNotNull(response)
//        Assert.assertTrue(resultResponse.equals(expectedresponse))
//    }
//


//    @Test
//    fun `Password is letter-only, returns error`() {
//    var a=  validatePassword.invoke()
//        assertEquals(a.toString(), false)
//    }
//    @Test
//    fun testConversionApiSuccess() {
//        mockedResponse = MockResponseFileReader("currency/conversion_success.json").content
//
//        server.enqueue(
//            MockResponse()
//                .setResponseCode(200)
//                .setBody(mockedResponse)
//        )
//
//        val response = runBlocking { repository.getAmount("INR","CAD","5000") }
//        val json = gson.toJson(response.body())
//
//        val resultResponse = JsonParser.parseString(json)
//        val expectedresponse = JsonParser.parseString(mockedResponse)
//
//        Assert.assertNotNull(response)
//        Assert.assertTrue(resultResponse.equals(expectedresponse))
//    }
//
//    @Test
//    fun testOtherCurrencyApiSuccess() {
//        mockedResponse = MockResponseFileReader("currency/othercurrency_success.json").content
//
//        server.enqueue(
//            MockResponse()
//                .setResponseCode(200)
//                .setBody(mockedResponse)
//        )
//
//        val response = runBlocking { repository.getOtherCurrencies(baseCurrency = "AED") }
//        val json = gson.toJson(response.body())
//
//        val resultResponse = JsonParser.parseString(json)
//        val expectedresponse = JsonParser.parseString(mockedResponse)
//
//        Assert.assertNotNull(response)
//        Assert.assertTrue(resultResponse.equals(expectedresponse))
//    }
//
//    @After
//    fun tearDown() {
//        server.shutdown()
//    }
}
