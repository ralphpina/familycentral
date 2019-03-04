package net.ralphpina.familycentral.tasks_view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.disposables.Disposable
import net.ralphpina.familycentral.family.FamilyRepository
import net.ralphpina.familycentral.task.Task
import net.ralphpina.familycentral.task.TasksRepository
import javax.inject.Inject

class TasksViewModel(val tasksRepository: TasksRepository): ViewModel() {
    @Suppress("UNCHECKED_CAST")
    class Factory @Inject constructor(private val tasksRepository: TasksRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>) =
            TasksViewModel(tasksRepository) as T
    }

    private val _tasks: MutableLiveData<List<Task>> by lazy {
        MutableLiveData<List<Task>>()
    }
    val tasks: LiveData<List<Task>>
        get() = _tasks

    private val disposable: Disposable

    init {
        disposable = tasksRepository.tasks()
            .doOnNext { _tasks.value = it }
            .subscribe()
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}