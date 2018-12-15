import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { TaskService } from '../../../services/task/task.service';
import { Task } from '../../../models/task.model';

@Component({
  selector: 'app-new-housework',
  templateUrl: './new-housework.component.html',
  styleUrls: ['./new-housework.component.sass'],
  providers: [TaskService]
})
export class NewHouseworkComponent implements OnInit {

  private currentUrl: string;
  private taskId: number;
  private task: Task;

  constructor(private taskService: TaskService,
              private router: Router,
              private activatedRoute: ActivatedRoute) { }

  private newHouseworkForm: FormGroup = new FormGroup({
    taskName: new FormControl(null, [Validators.required]),
    score: new FormControl(null, [Validators.min(1), Validators.required]),
    taskDescription: new FormControl(null, [Validators.required])
  });

  private async submitForm() {
    if (this.newHouseworkForm.invalid) {
      Object.keys(this.newHouseworkForm.controls).forEach(field => {
        const control = this.newHouseworkForm.get(field);
        control.markAsTouched({ onlySelf: true });
      });
    } else {
      if(this.currentUrl == '/new-housework') {
        await this.taskService.addTask(this.newHouseworkForm.value as Task);
      } else {
        await this.taskService.editTask(this.taskId, this.newHouseworkForm.value as Task);
      }
      
      this.router.navigate(['/housework-manager']);

    }
  }

  async ngOnInit() {
    this.currentUrl = this.router.url;
    
    if(this.currentUrl != '/new-housework') {
      this.taskId = this.activatedRoute.snapshot.params['id'];
      this.task = await this.taskService.getTask(this.taskId);

      this.newHouseworkForm.setValue({taskName: this.task.taskName, score: this.task.score, taskDescription: this.task.taskDescription});
      this.newHouseworkForm.controls.taskName.markAsDirty();
      this.newHouseworkForm.controls.score.markAsDirty();
      this.newHouseworkForm.controls.taskDescription.markAsDirty();
    }
  }

}
