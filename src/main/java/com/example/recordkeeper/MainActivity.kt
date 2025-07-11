package com.example.recordkeeper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.recordkeeper.ui.theme.RecordKeeperTheme
import com.example.recordkeeper.ui.compose.RecordKeeperApp
import com.example.recordkeeper.viewmodel.RecordViewModel

class MainActivity : ComponentActivity() {
    
    private lateinit var viewModel: RecordViewModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        viewModel = ViewModelProvider(this)[RecordViewModel::class.java]
        
        setContent {
            RecordKeeperTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RecordKeeperApp(viewModel = viewModel)
                }
            }
        }
    }
}