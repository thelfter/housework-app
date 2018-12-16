import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.sass']
})
export class SignupComponent implements OnInit {

  constructor() { }

  private selectedRoom: number;

  //private actualYear = new Date().getFullYear();


  //private months = ['Január', 'Február', 'Március', 'Április', 'Május', 'Június', 'Július', 'Augusztus', 'Szeptember', 'Október', 'November', 'December'];

  private signUpForm: FormGroup = new FormGroup({
    name: new FormControl(null, [Validators.required]),
    username: new FormControl(null, [Validators.minLength(4), Validators.required]),
    password: new FormControl(null, [Validators.minLength(6), Validators.required]),
    //year: new FormControl(null, [Validators.min(this.actualYear - 120), Validators.max(this.actualYear - 6), Validators.required]),
    //month: new FormControl(null, [Validators.required]),
    //day: new FormControl(null, [Validators.min(1), Validators.max(31), Validators.required]),
    roomId: new FormControl(null, [Validators.min(1), Validators.max(4), Validators.required])
  });

  private selectRoom(roomNumber: number) {
    this.selectedRoom = roomNumber;
    this.signUpForm.controls['roomId'].setValue(roomNumber);
  }

  private submitForm() {
    if (this.signUpForm.invalid) {
      Object.keys(this.signUpForm.controls).forEach(field => {
        const control = this.signUpForm.get(field);
        control.markAsTouched({ onlySelf: true });
      });
    } else {
      console.log(this.signUpForm);
    }
  }

  ngOnInit() {
    //this.signUpForm.controls['month'].setValue('0');
  }

}
