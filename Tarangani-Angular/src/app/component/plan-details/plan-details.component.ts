import { Component, OnInit } from '@angular/core';
import { PlanService } from '../../service/plan.service';
import { ActivatedRoute } from '@angular/router';
import { Plan } from '../../model/plan';

@Component({
  selector: 'app-plan-details',
  templateUrl: './plan-details.component.html',
  styleUrls: ['./plan-details.component.css']
})
export class PlanDetailsComponent implements OnInit {

  plan: Plan;
  femaleLogo: string;
  maleLogo: string;
  constructor(private planService: PlanService,
    private activatedRoute: ActivatedRoute
  ) {
  
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe(
      (params) => {
        let pTitle = params['pTitle'];
        if (pTitle) {
          this.planService.getPlanBypTitle(pTitle).subscribe(
            (data) => this.plan = data
          );
        }
      }
    );
  }

}