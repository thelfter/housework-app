import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../services/user/user.service';
import { AuthService } from '../../../auth.service';
import { User } from '../../../models/user.model';
import { Task } from '../../../models/task.model';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.sass'],
  providers: [UserService, AuthService]
})
export class ProfileComponent implements OnInit {

  constructor(private userService: UserService,
              private authService: AuthService) { }

  private user: User;
  private inProgressTaskScoreSum: number = 0;
  private userScore: number = 0;

  async ngOnInit() {
    this.user = this.authService.getUser;
    this.userScore = this.user.score;

    let userInProgressTasks: Task[] = this.user.tasks.filter(task => !task.isCompleted);
    

    for(let task of userInProgressTasks) {
      this.inProgressTaskScoreSum += +task.score;
    }
  }

}
