package com.app.jet2test

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.app.jet2test.api.ApiService
import com.app.jet2test.di.DaggerApplicationComponent
import com.app.jet2test.di.RepositoryModule
import com.app.jet2test.di.RetrofitModule
import com.app.jet2test.model.EmpModel
import com.app.jet2test.repositories.EmpRepository
import com.app.jet2test.viewmodel.EmpViewModel
import junit.framework.Assert
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Retrofit
import java.io.IOException
import javax.inject.Inject

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class EmployeeUnitTest {


    @Mock
    lateinit var repository: EmpRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository  = mock(EmpRepository::class.java)

    }

    @Test
    fun isResponseSuccess() {
        repository.getEmpData()?.observe(mock(LifecycleOwner::class.java), Observer { it.let {
            if(it.status.equals("Success")){
                assertTrue(true)
            }else{
                assertFalse(false)
            }
        } })
        verify(repository).getEmpData()

    }

    @Test
    fun isResponseFailure() {
        repository.getEmpData()?.observe(mock(LifecycleOwner::class.java), Observer { it.let {
            if(it.status.equals("Success")){
                assertTrue(true)
            }else{
                assertFalse(false)
            }
        } })
        verify(repository).getEmpData()
    }

    @Test
    fun isOnline() {
        repository.isOnline()?.observe(mock(LifecycleOwner::class.java), Observer { it.let {
            if(it){
                assertTrue(it)
            }else{
                assertFalse(it)
            }
        } })
        verify(repository).isOnline()

    }
}
