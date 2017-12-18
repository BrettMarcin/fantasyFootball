import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HttpModule} from '@angular/http';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms'
import 'rxjs/add/operator/map';

import { AppComponent } from './app.component';
import { TeamService } from './services/team.service';
import { HomeComponent } from './components/home/home.component';
import { DraftBeforeComponent } from './components/draft-before/draft-before.component';
import { CurrentDraftComponent } from './components/current-draft/current-draft.component';
import { AfterDraftComponent } from './components/after-draft/after-draft.component';

const appRoutes: Routes = [
  {path:'', component:HomeComponent}
];


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    DraftBeforeComponent,
    CurrentDraftComponent,
    AfterDraftComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [TeamService],
  bootstrap: [AppComponent]
})
export class AppModule { }
