import { Component, OnInit } from '@angular/core';
import {TeamService} from '../../services/team.service'
import {Team} from '../../models/team.interface'
import { FormGroup, FormControl, ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  draftStarted: boolean = false;

  constructor(private teamService: TeamService) {
  }

  ngOnInit() {

    this.teamService.draftStarted().subscribe(
      data => {
        this.draftStarted = data
      });
  }

}
