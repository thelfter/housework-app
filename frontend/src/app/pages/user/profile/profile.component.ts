import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../services/user/user.service';
import { TaskService } from '../../../services/task/task.service';
import { AuthService } from '../../../auth.service';
import { User } from '../../../models/user.model';
import { Task } from '../../../models/task.model';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.sass'],
  providers: [UserService, AuthService, TaskService]
})
export class ProfileComponent implements OnInit {

  constructor(private userService: UserService,
              private taskService: TaskService,
              private authService: AuthService) { }

  private user: User;
  private inProgressTaskScoreSum: number = 0;
  private sumScore: number = 0;
  private actualScore: number = 0;
  private inProgressTasks: Task[];
  private completedTasks: Task[];

  private async completeTask(taskId: number) {
    await this.taskService.completeTask(taskId);
    this.user = await this.authService.getUser(window.localStorage.getItem('user'));

    this.fetchData(this.user);
  }

  private async refuseTask(taskId: number) {
    this.user = await this.userService.unassignTask(this.user.id, taskId);
    this.fetchData(this.user);
  }

  private fetchData(user: User) {
    this.sumScore = this.user.sumScore;
    this.actualScore = this.user.actualScore;
    this.inProgressTasks = this.user.tasks.filter(task => !task.isCompleted);
    this.completedTasks = this.user.tasks.filter(task => task.isCompleted);

    this.inProgressTaskScoreSum = 0;
    for(let task of this.inProgressTasks) {
      this.inProgressTaskScoreSum += +task.score;
    }
  }

  async ngOnInit() {
    this.user = await this.authService.getUser(window.localStorage.getItem('user'));
    this.fetchData(this.user);
  }
}
