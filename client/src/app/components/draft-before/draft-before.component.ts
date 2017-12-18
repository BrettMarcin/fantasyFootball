import { Component, OnInit } from '@angular/core';
import {TeamService} from '../../services/team.service'
import {Team} from '../../models/team.interface'
import { FormGroup, FormControl, ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-draft-before',
  templateUrl: './draft-before.component.html',
  styleUrls: ['./draft-before.component.css']
})
export class DraftBeforeComponent implements OnInit {

  localTeam: Team = null;
  form: FormGroup;
  cpuForm: FormGroup;
  cpuName = new FormControl("", [Validators.required]);
  teamsInSession: Team[];
  name = new FormControl("", [Validators.required]);
  teamName = new FormControl("", Validators.required);

  constructor(private teamService: TeamService, fb: FormBuilder) {
    this.form = fb.group({
      "name": this.name,
      "teamName": this.teamName
    });
    this.cpuForm = fb.group({
      "cpuName": this.cpuName
    });
  }

  ngOnInit() {

    this.teamService.getTeams().subscribe(
      data => {
        this.teamsInSession = data;
      });

    this.teamService.getLocalTeam().subscribe(
      data => {
        this.localTeam = data;
      });
  }

  checkLocalTeam(){
    if (this.localTeam !== null) {
      return 'name' in this.localTeam;
    } else {
      return false;
    }
  }

  setLocalTeam(theTeam: Team){
    this.teamService.setLocalTeam(theTeam).subscribe(
      data => {
        this.localTeam = data;
      });
  }

}
