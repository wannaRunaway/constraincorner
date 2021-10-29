package com.example.constraincorner

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.constraincorner.databinding.ActivityMainBinding
import com.example.constraincorner.databinding.AdapterMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var adapter: RecyclerView.Adapter<MyViewHolder>
    private lateinit var list: List<Int>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        activityMainBinding.recyclerview.layoutManager = LinearLayoutManager(this)
        list = listOf(1,2,3,4,5,6,7,8,9,10)
        adapter = Myadapter(this, list)
        activityMainBinding.recyclerview.adapter = adapter
    }

    private class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var adapterMainBinding: AdapterMainBinding = AdapterMainBinding.bind(itemView)
        fun getAdapterMainBinding(): AdapterMainBinding{
            return adapterMainBinding
        }
    }

    private class Myadapter(var context: Context, var list: List<Int>): RecyclerView.Adapter<MyViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_main, parent, false))
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//            holder.getAdapterMainBinding()
//            val colorArr = arrayOf(Color.BLUE, Color.YELLOW, Color.DKGRAY, Color.GREEN, Color.RED, Color.LTGRAY)
//            val color = colorArr[Random.nextInt(colorArr.size)]
//            holder.getAdapterMainBinding().listItem.setBackgroundColor(color)
        }

        override fun getItemCount(): Int {
            return list.size
        }

    }
}