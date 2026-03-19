import { Routes } from '@angular/router';
import { authGuard } from './guards/auth-guard';
import { SignInComponent } from './components/sign-in/sign-in'; 
import { RequestFormComponent } from './components/request-form/request-form';
import { MySubmissionsComponent } from './components/my-submissions/my-submissions';
import { ManagementBoardComponent } from './components/management-board/management-board';
import { ActivityLogComponent } from './components/activity-log/activity-log';

export const routes: Routes = [
  { path: '', redirectTo: 'signin', pathMatch: 'full' },
  { path: 'signin', component: SignInComponent },
  { path: 'new-request', component: RequestFormComponent, canActivate: [authGuard] },
  { path: 'my-submissions', component: MySubmissionsComponent, canActivate: [authGuard] },
  { path: 'management', component: ManagementBoardComponent, canActivate: [authGuard] },
  { path: 'activity-log', component: ActivityLogComponent, canActivate: [authGuard] },
  { path: '**', redirectTo: 'signin' }
];