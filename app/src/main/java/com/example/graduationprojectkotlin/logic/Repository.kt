package com.example.graduationprojectkotlin.logic

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.liveData
import com.example.graduationprojectkotlin.GraduationProjectKotlinApplication
import com.example.graduationprojectkotlin.logic.dao.UserDao
import com.example.graduationprojectkotlin.logic.model.*
import com.example.graduationprojectkotlin.logic.network.*
import kotlinx.coroutines.Dispatchers
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback

import retrofit2.Response

object Repository {
   // 上传文件

    fun uploadFile(partList: List<MultipartBody.Part>) = liveData(Dispatchers.IO) {
        val result = try {
            val statusResponse = GraduationNetwork.uploadFile(partList)
            //判断是否得到数据
            if (statusResponse.status == "true") {
               // saveUser(userLoginResponse.user)
                Result.success(statusResponse)
            } else {
                //TODO 没有获取到数据或请求失败提示
                Result.failure(RuntimeException("response status is ${statusResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<StatusResponse>(e)
        }
        //TODO as需要么？
        emit(result as Result<StatusResponse>)
    }

    fun uploadImg(partList: List<MultipartBody.Part>) = liveData(Dispatchers.IO) {
        val result = try {
            val statusResponse = GraduationNetwork.uploadImg(partList)
            //判断是否得到数据
            if (statusResponse.status == "true") {
                // saveUser(userLoginResponse.user)
                Result.success(statusResponse)
            } else {
                //TODO 没有获取到数据或请求失败提示
                Result.failure(RuntimeException("response status is ${statusResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<StatusResponse>(e)
        }
        //TODO as需要么？
        emit(result as Result<StatusResponse>)
    }

//    fun uploadFile(partList: List<MultipartBody.Part>) {
//        val fileService = ServiceCreator.create(FileService::class.java)
//        fileService.uploadFile(partList).enqueue(object : Callback<ResponseBody> {
//            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//               // Log.d("123465", "失败1")
//                t.printStackTrace()
//            }
//
//            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                val userLoginResponse = response.body()
//                if (userLoginResponse != null) {
//                   // Log.d("123456", userLoginResponse.toString())
//                }
//            }
//
//        })
//    }
//    fun uploadImg(partList: List<MultipartBody.Part>) {
//        val fileService = ServiceCreator.create(FileService::class.java)
//        fileService.uploadImg(partList).enqueue(object : Callback<ResponseBody> {
//            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                // Log.d("123465", "失败1")
//                t.printStackTrace()
//            }
//
//            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                val userLoginResponse = response.body()
//                if (userLoginResponse != null) {
//                    // Log.d("123456", userLoginResponse.toString())
//                }
//            }
//
//        })
//    }

    fun login(email: String, password: String) = liveData(Dispatchers.IO) {
        val result = try {
            val userLoginResponse = GraduationNetwork.login(email, password)
            //判断是否得到数据
            if (userLoginResponse.status == "true") {
                saveUser(userLoginResponse.user)
                Result.success(userLoginResponse)
            } else {
                //TODO 没有获取到数据或请求失败提示
                Result.failure(RuntimeException("response status is ${userLoginResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<UserInfoResponse>(e)
        }
        //TODO as需要么？
        emit(result as Result<UserInfoResponse>)
    }

    fun register(number: String,name: String,password: String,email: String,school: String, sex: String) = liveData(Dispatchers.IO) {
        val result = try {
            val statusResponse = GraduationNetwork.register(number, name, password, email, school, sex)
            //判断是否得到数据
            if (statusResponse.status == "true") {
                //saveUser(userLoginResponse.user)
                Result.success(statusResponse)
            } else {
                //TODO 没有获取到数据或请求失败提示
                Result.failure(RuntimeException("response status is ${statusResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<StatusResponse>(e)
        }
        //TODO as需要么？
        emit(result as Result<StatusResponse>)
    }

    fun updateUserInfo(id:String,number: String,name: String,email: String,school: String, sex: String,picture: String) = liveData(Dispatchers.IO) {
        val result = try {
            val statusResponse = GraduationNetwork.updateUserInfo(id,number, name,email, school, sex)
            //判断是否得到数据
            if (statusResponse.status == "true") {
                //TODO 更新保存的数据  加上自动刷新
                if(picture.isNotEmpty()){
                    saveUser(User(id.toInt(),number,name,email,school,sex,picture))
                }else{
                    saveUser(User(id.toInt(),number,name,email,school,sex, getSavedUser().picture))

                }
                Result.success(statusResponse)
            } else {
                //TODO 没有获取到数据或请求失败提示
                Result.failure(RuntimeException("response status is ${statusResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<StatusResponse>(e)
        }
        //TODO as需要么？
        emit(result as Result<StatusResponse>)
    }

    fun alterPassword(id:String,password: String) = liveData(Dispatchers.IO) {
        val result = try {
            val statusResponse = GraduationNetwork.alterPassword(id,password)
            //判断是否得到数据
            if (statusResponse.status == "true") {
                //TODO 密码更改后记得重新登陆

                Result.success(statusResponse)
            } else {
                //TODO 没有获取到数据或请求失败提示
                Result.failure(RuntimeException("response status is ${statusResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<StatusResponse>(e)
        }
        //TODO as需要么？
        emit(result as Result<StatusResponse>)
    }

    fun getUserInfo(query: Int) = liveData(Dispatchers.IO) {
        val result = try {
            val userInfoResponse = GraduationNetwork.getUserInfo(query)
            if (userInfoResponse.status == "true") {
                val user = userInfoResponse.user
                Result.success(user)
            } else {
                Result.failure(RuntimeException("response status is ${userInfoResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<User>(e)
        }
        //TODO as需要么？
        emit(result as Result<User>)
    }

    fun createCourse(name:String,detail:String,owner:String,sort:String) = liveData(Dispatchers.IO) {
        val result = try {
            val statusResponse = GraduationNetwork.createCourse(name,detail,owner,sort)
            //判断是否得到数据
            if (statusResponse.status != "false") {
                //saveUser(userLoginResponse.user)
                Result.success(statusResponse)
            } else {
                //TODO 没有获取到数据或请求失败提示
                Result.failure(RuntimeException("response status is ${statusResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<StatusResponse>(e)
        }
        //TODO as需要么？
        emit(result as Result<StatusResponse>)
    }

    fun deleteCourse(id:String) = liveData(Dispatchers.IO) {
        val result = try {
            val statusResponse = GraduationNetwork.deleteCourse(id)
            //判断是否得到数据
            if (statusResponse.status == "true") {
                //saveUser(userLoginResponse.user)
                Result.success(statusResponse)
            } else {
                //TODO 没有获取到数据或请求失败提示
                Result.failure(RuntimeException("response status is ${statusResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<StatusResponse>(e)
        }
        //TODO as需要么？
        emit(result as Result<StatusResponse>)
    }

    fun deleteTask1(taskId:Int) {
        val taskService = ServiceCreator.create(TaskService::class.java)
        taskService.delete(taskId).enqueue(object : Callback<StatusResponse> {
                override fun onFailure(call: Call<StatusResponse>, t: Throwable) {
                    Toast.makeText(GraduationProjectKotlinApplication.context,"请求失败,请检查网络",Toast.LENGTH_SHORT).show()
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<StatusResponse>,
                    response: Response<StatusResponse>
                ) {
                    val statusResponse = response.body()
                    if (statusResponse != null) {
                        if (statusResponse.status=="true") {
                            Toast.makeText(GraduationProjectKotlinApplication.context,"删除成功",Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(GraduationProjectKotlinApplication.context,"删除失败",Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            })

    }

//    fun getName(userId:Int): String {
//        val userService = ServiceCreator.create(UserService::class.java)
//        var a="不知道"
//        userService.getInfo(userId).enqueue(object : Callback<UserInfoResponse> {
//            override fun onFailure(call: Call<UserInfoResponse>, t: Throwable) {
//                Toast.makeText(GraduationProjectKotlinApplication.context,"请求失败,请检查网络",Toast.LENGTH_SHORT).show()
//                t.printStackTrace()
//            }
//
//            override fun onResponse(
//                call: Call<UserInfoResponse>,
//                response: Response<UserInfoResponse>
//            ) {
//                val userInfoResponse = response.body()
//                if (userInfoResponse != null) {
//                    if (userInfoResponse.status=="true") {
//                        a=userInfoResponse.user.name
//                        Toast.makeText(GraduationProjectKotlinApplication.context,a,Toast.LENGTH_SHORT).show()
//                    } else {
//                        //Toast.makeText(GraduationProjectKotlinApplication.context,"删除失败",Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//
//        })
//        return a
//
//    }
//    fun userLogin(email: String, password: String): Boolean {
//        val userService = ServiceCreator.create(UserService::class.java)
//        var isLogin = false//子线程没办法
//        userService.userLogin(email, password)
//            .enqueue(object : Callback<UserLoginResponse> {
//                override fun onFailure(call: Call<UserLoginResponse>, t: Throwable) {
//                    Toast.makeText(GraduationProjectKotlinApplication.context,"请求失败,请检查网络",Toast.LENGTH_SHORT).show()
//                    t.printStackTrace()
//                }
//
//                override fun onResponse(
//                    call: Call<UserLoginResponse>,
//                    response: Response<UserLoginResponse>
//                ) {
//                    val userLoginResponse = response.body()
//                    if (userLoginResponse != null) {
//                        if (userLoginResponse.status=="true") {
//                            //TODO 要把值传出去
//                            saveUser(userLoginResponse.user)
//                            isLogin = true
//                            Toast.makeText(GraduationProjectKotlinApplication.context,"登陆成功",Toast.LENGTH_SHORT).show()
//                        } else {
//                            Toast.makeText(GraduationProjectKotlinApplication.context,"用户名或密码不正确",Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                }
//
//            })
//        return isLogin
//    }

    fun getAllCourses(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val courseResponse = GraduationNetwork.getAllCourses(query)
            if (courseResponse.status == "true") {
                val courses = courseResponse.courses
                Result.success(courses)
            } else {
                Result.failure(RuntimeException("response status is ${courseResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<List<Course>>(e)
        }
        //TODO as需要么？
        emit(result as Result<List<Course>>)
    }

    fun getUserCourses(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val courseResponse = GraduationNetwork.getUserCourses(query)
            if (courseResponse.status == "true") {
                val courses = courseResponse.courses
                Result.success(courses)
            } else {
                Result.failure(RuntimeException("response status is ${courseResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<List<Course>>(e)
        }
        //TODO as需要么？
        emit(result as Result<List<Course>>)
    }
    fun getCreatedCourses(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val courseResponse = GraduationNetwork.getCreatedCourses(query)
            if (courseResponse.status == "true") {
                val courses = courseResponse.courses
                Result.success(courses)
            } else {
                Result.failure(RuntimeException("response status is ${courseResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<List<Course>>(e)
        }
        //TODO as需要么？
        emit(result as Result<List<Course>>)
    }

    fun searchCourses(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val courseResponse = GraduationNetwork.searchCourses(query)
            if (courseResponse.status == "true") {
                val courses = courseResponse.courses
                Result.success(courses)
            } else {
                Result.failure(RuntimeException("response status is ${courseResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<List<Course>>(e)
        }
        //TODO as需要么？
        emit(result as Result<List<Course>>)
    }

    fun getCourseInfo(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val courseInfoResponse = GraduationNetwork.getCourseInfo(query)
            if (courseInfoResponse.status == "true") {
                val course = courseInfoResponse.course
                Result.success(course)
            } else {
                Result.failure(RuntimeException("response status is ${courseInfoResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<Course>(e)
        }
        //TODO as需要么？
        emit(result as Result<Course>)
    }



    //fun userLogin(data:UserLoginInfo):

    //TODO 获取课程任务
    fun getTasks(query: Int) = liveData(Dispatchers.IO) {
        val result = try {
            val taskResponse = GraduationNetwork.getTasks(query)
            if (taskResponse.status == "true") {
                val tasks = taskResponse.tasks
                Result.success(tasks)
            } else {
                Result.failure(RuntimeException("response status is ${taskResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<List<Task>>(e)
        }
        //TODO as需要么？
        emit(result as Result<List<Task>>)
    }

    fun deleteTask(id:Int) = liveData(Dispatchers.IO) {
        val result = try {
            val statusResponse = GraduationNetwork.deleteTasks(id)
            //判断是否得到数据
            if (statusResponse.status == "true") {
                //saveUser(userLoginResponse.user)
                Result.success(statusResponse)
            } else {
                //TODO 没有获取到数据或请求失败提示
                Result.failure(RuntimeException("response status is ${statusResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<StatusResponse>(e)
        }
        //TODO as需要么？
        emit(result as Result<StatusResponse>)
    }

    fun addTask(name: String,detail: String,courseId: Int) = liveData(Dispatchers.IO) {
        val result = try {
            val statusResponse = GraduationNetwork.addTasks(name,detail,courseId)
            //判断是否得到数据
            if (statusResponse.status != "false") {
                //saveUser(userLoginResponse.user)
                Result.success(statusResponse)
            } else {
                //TODO 没有获取到数据或请求失败提示
                Result.failure(RuntimeException("response status is ${statusResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<StatusResponse>(e)
        }
        //TODO as需要么？
        emit(result as Result<StatusResponse>)
    }


    //TODO 获取课程或任务的评论
    fun getCourseComments(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val commentResponse = GraduationNetwork.getCourseComments(query)
            if (commentResponse.status == "true") {
                val comments = commentResponse.comments
                Result.success(comments)
            } else {
                Result.failure(RuntimeException("response status is ${commentResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<List<com.example.graduationprojectkotlin.logic.model.Comment>>(e)
        }
        //TODO as需要么？
        emit(result as Result<List<Comment>>)
    }

    fun getTaskComments(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val commentResponse = GraduationNetwork.getTaskComments(query)
            if (commentResponse.status == "true") {
                val comments = commentResponse.comments
                Result.success(comments)
            } else {
                Result.failure(RuntimeException("response status is ${commentResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<List<com.example.graduationprojectkotlin.logic.model.Comment>>(e)
        }
        //TODO as需要么？
        emit(result as Result<List<Comment>>)
    }

    fun addCourseComment(userId: String,detail:String,courseId: String) = liveData(Dispatchers.IO) {
        val result = try {
            val statusResponse = GraduationNetwork.addCourseComment(userId,detail,courseId)
            //判断是否得到数据
            if (statusResponse.status == "true") {
                Result.success(statusResponse)
            } else {
                //TODO 没有获取到数据或请求失败提示
                Result.failure(RuntimeException("response status is ${statusResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<StatusResponse>(e)
        }
        //TODO as需要么？
        emit(result as Result<StatusResponse>)
    }

    fun addTaskComment(userId: String,detail:String,taskId: String) = liveData(Dispatchers.IO) {
        val result = try {
            val statusResponse = GraduationNetwork.addCourseComment(userId,detail,taskId)
            //判断是否得到数据
            if (statusResponse.status == "true") {
                Result.success(statusResponse)
            } else {
                //TODO 没有获取到数据或请求失败提示
                Result.failure(RuntimeException("response status is ${statusResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<StatusResponse>(e)
        }
        //TODO as需要么？
        emit(result as Result<StatusResponse>)
    }





    fun saveUser(user: User) = UserDao.saveUser(user)

    fun getSavedUser() = UserDao.getSavedUser()

    fun isUserSaved() = UserDao.isUserSaved()

    fun deleteUser() = UserDao.deleteUser()
}