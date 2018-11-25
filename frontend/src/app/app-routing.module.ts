import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingComponent } from './pages/guest/landing/landing.component';
import { LoginComponent } from './pages/guest/login/login.component';
import { SignupComponent } from './pages/guest/signup/signup.component';
import { HouseworkBrowserComponent } from './pages/user/housework-browser/housework-browser.component';
import { HouseworkDetailsComponent } from './pages/user/housework-details/housework-details.component';
import { ProfileComponent } from './pages/user/profile/profile.component';
import { HouseworksComponent } from './pages/owner/houseworks/houseworks.component';
import { UserListComponent } from './pages/owner/user-list/user-list.component';
import { UserProfileComponent } from './pages/owner/user-profile/user-profile.component';

const routes: Routes = [
  { path: '', component: LandingComponent },
  { path: 'login', component: LoginComponent },
  { path: 'sign-up', component: SignupComponent },
  { path: 'housework-browser', component: HouseworkBrowserComponent },
  { path: 'housework/:id', component: HouseworkDetailsComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'owner/houseworks', component: HouseworksComponent },
  { path: 'owner/user-list', component: UserListComponent },
  { path: 'owner/user/:id', component: UserProfileComponent },
  { path: 'owner', redirectTo: 'owner/houseworks' },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }

