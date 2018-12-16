import { Component, OnInit } from '@angular/core';
import { TaskService } from '../../../services/task/task.service';
import { UserService } from '../../../services/user/user.service';
import { Task } from '../../../models/task.model';
import { User } from '../../../models/user.model';

@Component({
  selector: 'app-houseworks',
  templateUrl: './houseworks.component.html',
  styleUrls: ['./houseworks.component.sass'],
  providers: [TaskService, UserService]
})
export class HouseworksComponent implements OnInit {

  private users: User[];
  private tasks: Task[];
  private tasksToReview: Task[];
  private notCompletedTasks: Task[];

  constructor(private taskService: TaskService,
              private userService: UserService) { }

  private async approveTask(task: Task) {
    console.log(task);
    await this.taskService.approveTask(task.id);
    
    const user: User = this.getAssignedUser(task.id);
    await this.userService.increasePoints(user.id, +task.score);
    console.log(user.id);

    this.tasks = await this.taskService.getTasks() as Task[];
    this.fetchData(this.tasks);
  }

  private async rejectTask(userId: number, taskId: number) {
    await this.userService.unassignTask(userId, taskId);
    this.tasks = await this.taskService.getTasks() as Task[];
    this.fetchData(this.tasks);
  }

  private getAssignedUser(taskId: number): User {
    if(taskId) {
      for(let user of this.users) {
        for(let task of user.tasks){
          if(task.id == taskId) return user;
        }
      }
    }
    return null;
  }

  private async fetchData(tasks: Task[]) {
    this.notCompletedTasks = this.tasks.filter((task) => !task.isCompleted);
    this.tasksToReview = this.tasks.filter(task => (task.isCompleted && !task.approved));
  }

  async ngOnInit() {

    this.users = await this.userService.getUsers() as User[];
    this.tasks = await this.taskService.getTasks() as Task[];

    this.fetchData(this.tasks);
  }

}
