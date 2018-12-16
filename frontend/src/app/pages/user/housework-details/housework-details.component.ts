import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { TaskService } from '../../../services/task/task.service';
import { UserService } from '../../../services/user/user.service';
import { AuthService } from '../../../auth.service';
import { Task } from '../../../models/task.model';
import { User } from '../../../models/user.model';

@Component({
  selector: 'app-housework-details',
  templateUrl: './housework-details.component.html',
  styleUrls: ['./housework-details.component.sass'],
  providers: [TaskService, UserService, AuthService]
})
export class HouseworkDetailsComponent implements OnInit {

  private task: Task;

  private taskDescription: string;
  private taskName: string;
  private score: number;
  private taskId: number;

  constructor(private taskService: TaskService,
              private userService: UserService,
              private authService: AuthService,
              private router: Router,
              private activatedRoute: ActivatedRoute) { }

  private async addTask() {
    let user: User;
    this.authService.getUser(window.localStorage.getItem('user')).catch((e) => { console.log(e) }).then((user) => {
      user = user as User;
      this.userService.assignTask(user.id, this.taskId).catch((e) => { console.log(e) }).then(() => {
        this.router.navigate(['/profile']);
      })
    })
    
  }

  async ngOnInit() {
    this.taskId = this.activatedRoute.snapshot.params['id'];

    this.task = await this.taskService.getTask(this.taskId);

    this.taskDescription = this.task.taskDescription;
    this.taskName = this.task.taskName;
    this.score = +this.task.score;
  }

}
