package pl.edu.agh.ask.mapper;


import org.springframework.stereotype.Component;
import pl.edu.agh.ask.domain.Task;
import pl.edu.agh.ask.domain.TaskDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskMapper {

    public Task mapToTask(final TaskDto taskDto){
        return new Task(
            taskDto.getId(),
            taskDto.getTitle(),
            taskDto.getContent(), taskDto.getUserId()
        );
    }

    public TaskDto mapToTaskDto(final Task task){
        return new TaskDto(
            task.getId(),
            task.getTitle(),
            task.getContent(),
                task.getUserId()
        );
    }

    public List<TaskDto> mapToTaskDtoList(final List<Task> taskList){
         return taskList.stream()
                .map(n->new TaskDto(n.getId(), n.getTitle(), n.getContent(), n.getUserId()))
                .collect(Collectors.toList());
    }

}
