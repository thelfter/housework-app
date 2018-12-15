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
  private sumScore: number = 0;
  private actualScore: number = 0;
  private inProgressTasks: Task[];
  private completedTasks: Task[];

  async ngOnInit() {
    this.user = await this.authService.getUser(window.localStorage.getItem('user'));
    
    this.sumScore = this.user.sumScore;
    this.actualScore = this.user.actualScore;

    console.log(this.user.tasks);

    this.inProgressTasks = this.user.tasks.filter(task => !task.isCompleted);

    this.completedTasks = this.user.tasks.filter(task => task.isCompleted);
    

    for(let task of this.inProgressTasks) {
      this.inProgressTaskScoreSum += +task.score;
    }
    
  }
}
