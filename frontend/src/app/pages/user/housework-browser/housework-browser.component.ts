import { Component, OnInit } from '@angular/core';
import { TaskService } from '../../../services/task/task.service';
import { Task } from '../../../models/task.model';

@Component({
  selector: 'app-housework-browser',
  templateUrl: './housework-browser.component.html',
  styleUrls: ['./housework-browser.component.sass'],
  providers: [TaskService]
})
export class HouseworkBrowserComponent implements OnInit {

  private tasks: Task[];
  private availableTasks: Task[];

  constructor(private taskService: TaskService) { }

  async ngOnInit() {
    this.tasks = await this.taskService.getTasks() as Task[];

    this.availableTasks = this.tasks.filter(task => task.available);

    console.log(this.tasks);
  }

}
