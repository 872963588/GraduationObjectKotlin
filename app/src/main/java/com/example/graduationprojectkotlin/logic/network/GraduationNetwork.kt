package com.example.graduationprojectkotlin.logic.network

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object GraduationNetwork {

    private val fileService = ServiceCreator.create(FileService::class.java)

    //搜索课程任务信息 TODO 添加参数
    suspend fun upload(partList: List<MultipartBody.Part>) =
        fileService.upload(partList).await()


    private val taskService = ServiceCreator.create(TaskService::class.java)

    //搜索课程任务信息 TODO 添加参数
    suspend fun getTasks(query: Int) = taskService.getTasks(query).await()
    suspend fun addTasks(name: String,time: String,detail: String,courseId: Int) = taskService.add(name,time,detail,courseId).await()
    suspend fun deleteTasks(query: Int) = taskService.delete(query).await()



    private val userService = ServiceCreator.create(UserService::class.java)
    //搜索课程任务信息 TODO 添加参数
//    suspend fun userLogin(loginInfo: LoginInfo) = userService.userLogin(loginInfo).await()
    suspend fun login(email: String, password: String) =
        userService.login(email, password).await()
    suspend fun register(number: String,name: String,password: String,email: String,school: String, sex: String) =
        userService.register(number, name, password, email, school, sex).await()
    suspend fun updateUserInfo(id: String,number: String,name: String,email: String,school: String, sex: String) =
        userService.update(id,number, name, email, school, sex).await()
    suspend fun alterPassword(id: String,password: String) =
        userService.alterPassword(id,password).await()




    //创建UserService接口的动态代理对象
    private val courseService = ServiceCreator.create(CourseService::class.java)
    //搜索课程信息 TODO 添加参数
    suspend fun getAllCourses(query: String) = courseService.getAllCourses(query).await()

    suspend fun getUserCourses(query: String) = courseService.getUserCourses(query).await()
    suspend fun getCreatedCourses(query: String) = courseService.getCreatedCourses(query).await()
    suspend fun getCourseInfo(query: String) = courseService.getCourseInfo(query).await()
    suspend fun createCourse(name:String,detail:String,owner:String,sort:String) = courseService.createCourse(name,detail,owner,sort).await()
    suspend fun searchCourses(query: String) = courseService.searchCourses(query).await()
    suspend fun deleteCourse(id: String) = courseService.deleteCourse(id).await()



    //创建UserService接口的动态代理对象
    private val commentService = ServiceCreator.create(CommentService::class.java)
    //搜索课程信息 TODO 添加参数
    suspend fun getCourseComments(query: String) = commentService.getCourseComments(query).await()
    suspend fun getTaskComments(query: String) = commentService.getTaskComments(query).await()


    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException("response body is null"))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }


}