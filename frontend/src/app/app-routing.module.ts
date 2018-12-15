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
import { NewHouseworkComponent } from './pages/owner/new-housework/new-housework.component';
import { UserGuard } from './user.guard';
import { OwnerGuard } from './owner.guard';

const routes: Routes = [
  { path: '', component: LandingComponent },
  { path: 'login', component: LoginComponent },
  { path: 'sign-up', component: SignupComponent },
  { path: 'housework-browser', component: HouseworkBrowserComponent, canActivate: [UserGuard] },
  { path: 'housework/:id', component: HouseworkDetailsComponent, canActivate: [UserGuard] },
  { path: 'profile', component: ProfileComponent, canActivate: [UserGuard] },
  { path: 'housework-manager', component: HouseworksComponent, canActivate: [OwnerGuard] },
  { path: 'user-list', component: UserListComponent, canActivate: [OwnerGuard] },
  { path: 'user/:id', component: UserProfileComponent, canActivate: [OwnerGuard] },
  { path: 'edit-housework/:id', component: NewHouseworkComponent, canActivate: [OwnerGuard] },
  { path: 'new-housework', component: NewHouseworkComponent, canActivate: [OwnerGuard] },
  { path: 'owner', redirectTo: 'houseworks' },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }

