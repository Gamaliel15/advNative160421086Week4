package com.gamaliel.advweek4160421086.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.gamaliel.advweek4160421086.R
import com.gamaliel.advweek4160421086.databinding.StudentListItemBinding
import com.gamaliel.advweek4160421086.model.Student

class StudentListAdapter(val studentList:ArrayList<Student>)
    :RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(), ButtonDetailClickListener{
    class StudentViewHolder(var view: StudentListItemBinding)
        :RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<StudentListItemBinding>(inflater
            , R.layout.student_list_item,parent,false)
        return StudentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {

        holder.view.student = studentList[position]
        holder.view.listener = this



//        holder.binding.txtId.text = studentList[position].id
//        holder.binding.txtName.text = studentList[position].name
//        holder.binding.btnDetail.setOnClickListener{
//            val action = StudentListFragmentDirections.actionstudentdetailfragment(holder.binding.txtId.text.toString())
//            Navigation.findNavController(it).navigate(action)
//        }
//
//        val picasso = Picasso.Builder(holder.itemView.context)
//        picasso.listener { picasso, uri, exception ->
//            exception.printStackTrace()
//        }
//        picasso.build().load(studentList[position].photoUrl).into(holder.binding.imageViewGame, object:Callback{
//            override fun onSuccess() {
//                holder.binding.progressBar.visibility = View.INVISIBLE
//                holder.binding.imageViewGame.visibility = View.VISIBLE
//
//            }
//
//            override fun onError(e: Exception?) {
//                Log.e("picasso_error", e.toString())
//            }
//
//        })

    }

    override fun onButtonDetailClick(v: View) {
        val studentId = v.tag as String // dapat student id dan diubah jadi str
        val action = StudentListFragmentDirections.actionstudentdetailfragment(studentId) // pass id
        Navigation.findNavController(v).navigate(action)
    }

    fun updateStudentList(newStudentList:ArrayList<Student>){
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }
}