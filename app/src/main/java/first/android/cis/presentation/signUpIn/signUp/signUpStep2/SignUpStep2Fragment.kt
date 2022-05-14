package first.android.cis.presentation.signUpIn.signUp.signUpStep2

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.navArgs
import first.android.cis.R
import first.android.cis.databinding.FragmentSignUpStep2Binding
import com.cis.domain.models.user.UserSignInfo
//import com.example.data.network.services.SignUpService
import com.cis.domain.usecases.signInUp.CheckInputData
import first.android.cis.presentation.signUpIn.signUp.SignUpStep2FragmentArgs
import first.android.cis.presentation.signUpIn.signUp.SignUpStep2FragmentDirections
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

private const val ERROR_MESSAGE = "Ошибка! Данные введены неверно!"

class SignUpStep2Fragment : Fragment() {
    private val step2ViewModel by viewModel<Step2ViewModel>()
    private val args: SignUpStep2FragmentArgs by navArgs()
    lateinit var selectedItem: String
    private var dateOfBirth: String = ""
    private var _binding: FragmentSignUpStep2Binding? = null
    private val binding get() = _binding!!
    private lateinit var endSignUpButton: Button
    private lateinit var datePickerBtn: Button
    private lateinit var nameEditTextSignUp: EditText
    private lateinit var surnameEditSignUp: EditText
    private lateinit var spinnerTown: Spinner
    private lateinit var userNameTextView: TextView
    private lateinit var userSurnameTextView: TextView
    private lateinit var townTextView: TextView
    private lateinit var dateTextView: TextView
    private val checkInputData = CheckInputData()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSignUpStep2Binding.inflate(inflater, container, false)
        binding.let{
            endSignUpButton = it.endSignUpButton
            datePickerBtn = it.datePickerBtn
            nameEditTextSignUp = it.nameEditTextSignUp
            surnameEditSignUp = it.surnameEditSignUp
            spinnerTown = it.spinnerTown
            userNameTextView = it.userNameTextView
            userSurnameTextView = it.userSurnameTextView
            townTextView = it.townTextView
            dateTextView = it.dateTextView
        }
        val action = SignUpStep2FragmentDirections.actionSignUpStep2FragmentToNavigationNews()
        endSignUpButton.setOnClickListener{
            if (checkUserName() and checkUserSurname() and checkDateOfBirth() and checkUserTown()){
                //val signUpService = SignUpService(requireActivity().applicationContext)
                val userSignInfo = UserSignInfo(args.email, args.password,
                    nameEditTextSignUp.text.toString(),
                    surnameEditSignUp.text.toString(),
                    selectedItem, dateOfBirth
                )
                step2ViewModel.createUser(userSignInfo = userSignInfo)
            }
        }
        step2ViewModel.createUserResponse.observe(viewLifecycleOwner){ response ->
            if(response.isSuccessful){
                goInApp(action)
            } else {
                Toast.makeText(activity, "Ошибка подключения", Toast.LENGTH_LONG).show()
            }
        }
        datePickerConfig()
        return binding.root
    }
    private fun goInApp(action: NavDirections) {

    }

    private fun checkUserName(): Boolean{
        userNameTextView.setTextColor(Color.BLACK)
        val userName = nameEditTextSignUp.text.toString()
        if(checkInputData.checkUserName(userName = userName)){
            return true
        }else{
            userNameTextView.setTextColor(Color.RED)
            return false
        }
    }

    private fun checkUserSurname(): Boolean{
        userSurnameTextView.setTextColor(Color.BLACK)
        val userSurname = surnameEditSignUp.text.toString()
        if(checkInputData.checkUserName(userName = userSurname)){
            return true
        }else{
            userSurnameTextView.setTextColor(Color.RED)
            return false
        }
    }

    private fun checkDateOfBirth(): Boolean{
        dateTextView.setTextColor(Color.BLACK)
        if(checkInputData.checkDateofBirth(dateBirth = dateOfBirth)){
            return true
        }else{
            dateTextView.setTextColor(Color.RED)
            return false
        }
    }

    private fun checkUserTown(): Boolean{
        townTextView.setTextColor(Color.BLACK)
        val userTown = selectedItem
        if(checkInputData.checkUserTown(town = userTown)){
            return true
        }else {
            townTextView.setTextColor(Color.RED)
            return false
        }
    }

    private fun datePickerConfig() {
        //Calendar
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        //DatePicker
        datePickerBtn.setOnClickListener{
            val datePickerDialog = DatePickerDialog(requireContext(),
                R.style.ScrollingDatePicker,
                DatePickerDialog.OnDateSetListener{ _, myYear, myMonth, myDay ->
                    var monthDate = myMonth
                    monthDate += 1
                    dateOfBirth = "$myDay/$monthDate/$myYear"
                    datePickerBtn.text = dateOfBirth
                }, year, month, day)
            datePickerDialog.show()
        }
        spinnerConfig()
    }

    private fun spinnerConfig() {
        //Создаем адаптер для спиннера
        val spinnerAdapter: ArrayAdapter<*> = ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.towns,
            R.layout.my_spinner
        )
        spinnerAdapter.setDropDownViewResource(R.layout.my_spinner)
        spinnerTown.adapter = spinnerAdapter
        //Обработка выбора значения в спинере
        spinnerTown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                selectedItem = parent.getItemAtPosition(pos).toString()
            }
            override fun onNothingSelected(parent: AdapterView<out Adapter>?) {
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}