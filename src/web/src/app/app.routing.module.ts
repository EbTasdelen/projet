import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {AppLayoutComponent} from "./_layout/app-layout/app-layout.component";
import { NotfoundPageComponent } from './shared/notfound-page/notfound-page.component';
import { AuthGuard } from './security/auth-guard';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [
  {
    path: '', component: AppLayoutComponent, canActivate: [AuthGuard],
    children: [
      {path: '', pathMatch: 'full', redirectTo: 'product'},
      {path: 'product', loadChildren: './pages/product/product.module#ProductModule'},
      {path: 'issue', loadChildren: './pages/issue/issue.module#IssueModule'},
      {path: 'project', loadChildren: './pages/project/project.module#ProjectModule'}
    ]
  },
  {path: 'login', component : LoginComponent},
  {path: 'register', component : RegisterComponent},
  {path: '**', component : NotfoundPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}