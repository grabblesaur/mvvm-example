package com.example.mvvm_example.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.mvvm_example.R
import com.example.mvvm_example.databinding.ActivityTipCalculatorBinding
import com.example.mvvm_example.viewmodel.CalculatorViewModel
import com.google.android.material.snackbar.Snackbar

import kotlinx.android.synthetic.main.activity_tip_calculator.*

class TipCalculatorActivity : AppCompatActivity(),
    SaveDialogFragment.Callback,
    LoadDialogFragment.Callback {

    lateinit var binding: ActivityTipCalculatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tip_calculator)
        binding.vm = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)
        setSupportActionBar(toolbar)
    }

    override fun onSaveTip(name: String) {
        Snackbar.make(binding.root, "Saved $name", Snackbar.LENGTH_SHORT).show()
        binding.vm?.saveCurrentTip(name)
    }

    override fun onTipSelected(name: String) {
        binding.vm?.loadTipCalculation(name)
        Snackbar.make(binding.root, "Loaded $name", Snackbar.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_tip_calculator, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            R.id.action_save -> {
                showSaveDialog()
                true
            }
            R.id.action_load -> {
                showLoadDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showSaveDialog() {
        val saveDialogFragment = SaveDialogFragment()
        saveDialogFragment.show(supportFragmentManager, SaveDialogFragment::class.java.simpleName)
    }

    private fun showLoadDialog() {
        val dialog = LoadDialogFragment()
        dialog.show(supportFragmentManager, LoadDialogFragment::class.java.simpleName)
    }
}
