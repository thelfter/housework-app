import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-new-housework',
  templateUrl: './new-housework.component.html',
  styleUrls: ['./new-housework.component.sass']
})
export class NewHouseworkComponent implements OnInit {

  constructor() { }

  private newHouseworkForm: FormGroup = new FormGroup({
    taskName: new FormControl(null, [Validators.required]),
    points: new FormControl(null, [Validators.min(1), Validators.required]),
    description: new FormControl(null, [Validators.required])
  });

  private submitForm() {
    if (this.newHouseworkForm.invalid) {
      Object.keys(this.newHouseworkForm.controls).forEach(field => {
        const control = this.newHouseworkForm.get(field);
        control.markAsTouched({ onlySelf: true });
      });
    } else {
      console.log(this.newHouseworkForm);
    }
  }

  ngOnInit() {
  }

}
