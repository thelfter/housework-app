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
  private completedTasks: Task[];

  constructor(private taskService: TaskService,
              private userService: UserService) { }

  async ngOnInit() {

    this.users = await this.userService.getUsers() as User[];
    this.tasks = await this.taskService.getTasks() as Task[];

    this.completedTasks = this.tasks.filter(task => task.isCompleted);

  }

}
