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
              private activatedRoute: ActivatedRoute,
              private router: Router) { }

  private user: User;
  private inProgressTaskScoreSum: number = 0;
  private sumScore: number = 0;
  private actualScore: number = 0;
  private fullName: string = '';
  private inProgressTasks: Task[];
  private completedTasks: Task[];

  private fetchData(user: User) {
    
    this.sumScore = this.user.sumScore;
    this.actualScore = this.user.actualScore;
    this.fullName = this.user.fullName;

    this.inProgressTasks = this.user.tasks.filter(task => !task.isCompleted);
    this.completedTasks = this.user.tasks.filter(task => task.isCompleted);

    this.inProgressTaskScoreSum = 0;
    for(let task of this.inProgressTasks) {
      this.inProgressTaskScoreSum += +task.score;
    }
  }

  private async removeUser() {
    const userId = this.activatedRoute.snapshot.params['id'];
    await this.userService.removeUser(userId);
    this.router.navigate(['/user-list']);
  }

  async ngOnInit() {
    const userId = this.activatedRoute.snapshot.params['id'];
    this.user = await this.userService.getUser(userId);

    this.fetchData(this.user);
  }

}
