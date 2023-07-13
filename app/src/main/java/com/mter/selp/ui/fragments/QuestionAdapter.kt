package com.mter.selp.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mter.selp.R
import com.mter.selp.databinding.QuestionItemBinding

class QuestionAdapter : RecyclerView.Adapter<QuestionAdapter.QuestionHolder>() {

    val questionList = ArrayList<Question>()
    class QuestionHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = QuestionItemBinding.bind(item)
        fun bind(question: Question){
            binding.titleAnswer.text = question.title
            binding.answerOption1.text = question.answer_1
            binding.answerOption2.text = question.answer_2
            binding.answerOption3.text = question.answer_3
            binding.answerOption4.text = question.answer_4
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.question_item, parent, false)
        return QuestionHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionHolder, position: Int) {
        holder.bind(questionList[position])
    }

    override fun getItemCount(): Int {
        return  questionList.size
    }

    fun addQuestion(question: Question){
        questionList.add(question)
        notifyDataSetChanged()
    }
}