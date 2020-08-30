import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {AppComponent} from "./app.component";
import {AppRoutingModule} from "./app.routing.module";
import {AppLayoutComponent} from "./_layout/app-layout/app-layout.component";
import {FooterComponent} from "./_layout/footer/footer.component";
import {HeaderComponent} from "./_layout/header/header.component";
import {SidebarComponent} from "./_layout/sidebar/sidebar.component";
import {BsDatepickerModule, BsDropdownModule, CollapseModule, ModalModule, PaginationModule} from "ngx-bootstrap";
import {ToastNoAnimation, ToastNoAnimationModule, ToastrModule} from "ngx-toastr";
import { ApiService } from './services/api.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import{ NgxDatatableModule} from '@swimlane/ngx-datatable';
import { ProjectService } from './services/shared/project.service';
import { IssueService } from './services/shared/issue.service';
import { UserService } from './services/shared/user.service';
import { IssueHistoryService } from './services/shared/issuehistory.service';
import { NotfoundPageComponent } from './shared/notfound-page/notfound-page.component';
import { JwtInterceptor } from './security/jwt.interceptor';
import { AuthenticationService } from './security/authentification.service';
import { AuthGuard } from './security/auth-guard';
import { ErrorInterceptor } from './security/authentification-interceptor';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    AppComponent,
    AppLayoutComponent,
    FooterComponent,
    HeaderComponent,
    SidebarComponent,
    NotfoundPageComponent,
    LoginComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    AppRoutingModule,
    CollapseModule.forRoot(),
    BsDropdownModule.forRoot(),
    ModalModule.forRoot(),
    PaginationModule.forRoot(),
    BsDatepickerModule.forRoot(),
    ToastNoAnimationModule,
    NgxDatatableModule,
    ToastrModule.forRoot({
      toastComponent: ToastNoAnimation,
      maxOpened: 1,
      autoDismiss: true
    }),
  ],
  providers: [ApiService, ProjectService,IssueService,UserService,IssueHistoryService,AuthenticationService,AuthGuard,
 {provide:HTTP_INTERCEPTORS, useClass : JwtInterceptor, multi :true},
 {provide:HTTP_INTERCEPTORS, useClass : ErrorInterceptor, multi :true},

],
  bootstrap: [AppComponent]
})
export class AppModule { }