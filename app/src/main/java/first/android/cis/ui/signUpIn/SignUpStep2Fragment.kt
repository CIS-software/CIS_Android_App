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
import first.android.cis.models.users.AuthData
import first.android.cis.network.Retrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class SignUpStep2Fragment : Fragment() {
    private val args: SignUpStep2FragmentArgs by navArgs()
    lateinit var selectedItem: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val root = inflater.inflate(R.layout.fragment_sign_up_step2, container, false)
        val signUpBtn: Button = root.findViewById(R.id.endSignUpButton)
        val datePickerBtn: Button = root.findViewById(R.id.datePickerBtn)
        val testTV: TextView = root.findViewById(R.id.testTV) //TODO: Убрать тестовый текствью
        val nameEditText: EditText = root.findViewById(R.id.nameEditTextSignUp)
        val surnameEditText: EditText = root.findViewById(R.id.surnameEditSignUp)
        signUpBtn.setOnClickListener{
            val authData = AuthData(args.email, args.password)
            CoroutineScope(Dispatchers.Main).launch {
                Retrofit.usersApi.createUserAuth(authData)
            }
        }
        datePickerConfig(datePickerBtn, testTV, root)
        return root
    }

    private fun datePickerConfig(datePickerBtn: Button, testTV: TextView, root: View) {
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
                    testTV.text = "$myDay/$myMonth/$myYear"
                }, year, month, day)
            datePickerDialog.show()
        }
        spinnerConfig(root)
    }

    private fun spinnerConfig(root: View) {
        val spinner: Spinner = root.findViewById(R.id.spinnerTown)
        //Создаем адаптер для спиннера
        val spinnerAdapter: ArrayAdapter<*> = ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.towns,
            R.layout.my_spinner
        )
        spinnerAdapter.setDropDownViewResource(R.layout.my_spinner)
        spinner.adapter = spinnerAdapter
        //Обработка выбора значения в спинере
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                selectedItem = parent.getItemAtPosition(pos).toString()
            }
            override fun onNothingSelected(parent: AdapterView<out Adapter>?) {
            }
        }
    }
}