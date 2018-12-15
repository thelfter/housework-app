import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from '../../../services/user/user.service';
import { User } from '../../../models/user.model';
import { Task } from '../../../models/task.model';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.sass'],
  providers: [UserService]
})
export class UserProfileComponent implements OnInit {

  constructor(private userService: UserService,
              private activatedRoute: ActivatedRoute) { }

  private user: User;
  private inProgressTaskScoreSum: number = 0;
  private sumScore: number = 0;
  private actualScore: number = 0;
  private fullName: string = '';
  private inProgressTasks: Task[];

  async ngOnInit() {
    const userId = this.activatedRoute.snapshot.params['id'];
    this.user = await this.userService.getUser(userId);
    this.sumScore = this.user.sumScore;
    this.actualScore = this.user.actualScore;
    this.fullName = this.user.fullName;

    this.inProgressTasks = this.user.tasks.filter(task => !task.isCompleted);
    console.log(this.user.tasks);
    

    for(let task of this.inProgressTasks) {
      this.inProgressTaskScoreSum += +task.score;
    }
  }

}
