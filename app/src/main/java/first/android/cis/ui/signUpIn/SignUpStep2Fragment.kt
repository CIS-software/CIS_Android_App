package first.android.cis.ui.signUpIn

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import first.android.cis.R
import first.android.cis.databinding.FragmentSignUpStep2Binding
import first.android.cis.models.users.AuthData
import first.android.cis.models.users.UserSignInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class SignUpStep2Fragment : Fragment() {
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
        endSignUpButton.setOnClickListener{
            val userName: String = nameEditTextSignUp.text.toString()
            val userSurname: String = surnameEditSignUp.text.toString()
            val checkInputData = CheckInputData()
            if (checkInputData.checkUserInfo(userName, userSurname,
                    userNameTextView, userSurnameTextView, activity,
                    dateOfBirth, selectedItem, townTextView, dateTextView)){
                val userInfo = UserSignInfo(args.email, args.password,
                    nameEditTextSignUp.text.toString(),
                    surnameEditSignUp.text.toString(),
                    selectedItem, dateOfBirth
                )
                CoroutineScope(Dispatchers.Main).launch {
                    val authData = AuthData(userInfo.eMail, userInfo.password)
                    val signUpService = SignUpService()
                    val actionNavigate = SignUpStep2FragmentDirections.actionSignUpStep2FragmentToNavigationNews()
                    signUpService.signUp(userInfo, requireActivity(), authData, endSignUpButton, actionNavigate)
                }
            }
        }
        datePickerConfig()
        return binding.root
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