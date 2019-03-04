package net.ralphpina.familycentral.task

import io.reactivex.Completable
import io.reactivex.Observable
import net.ralphpina.familycentral.family.Member

data class Task(
    val id: String,
    val title: String,
    val description: String,
    val todos: List<Todo>,
    val assignedTo: Member,
    val mentions: List<Member>
)

data class Todo(
    val id: String,
    val description: String,
    val dueDate: Long,
    val assignedTo: Member,
    val isDone: Boolean
)

interface TasksRepository {
    fun insertOrUpdate(task: Task): Completable
    fun delete(task: Task): Completable
    fun tasks(): Observable<List<Task>>
}