import { Component, OnInit, TemplateRef } from '@angular/core';
import { IssueService } from 'src/app/services/shared/issue.service';
import { Page } from 'src/app/common/page';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ProjectService } from 'src/app/services/shared/project.service';
import { BsModalService, BsModalRef } from 'ngx-bootstrap';

@Component({
  selector: 'app-issue',
  templateUrl: './issue.component.html',
  styleUrls: ['./issue.component.scss']
})
export class IssueComponent implements OnInit {
  modalRef: BsModalRef;
  page= new Page();
  rows=[];
  projectOptions=[];

  issueForm : FormGroup;

  constructor(private issueService : IssueService,
    private projectService :ProjectService,
    private modalService: BsModalService,
    private formBuilder: FormBuilder
    ) { }

  ngOnInit() {
    this.issueForm = this.formBuilder.group({
projectId : [null, [Validators.required]],
description : [null, [Validators.required]]

    });
this.loadProjects();
this.setPage({offset:0});
   
  }

  private loadProjects(){
    this.projectService.getAll().subscribe(response =>{
      this.projectOptions= response;
    });
  }


  setPage(pageInfo){
    this.page.page=pageInfo.offset;
    this.issueService.getAll(this.page).subscribe(pageData =>{
      this.page.size =pageData.size;
      this.page.page =pageData.page;
      this.page.totalElements= pageData.totalElements;
      this.rows=pageData.content;
    });

  }

  get f(){ return this.issueForm.controls}

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }
  closeAndResetModel(){
    this.issueForm.reset();
    this.modalRef.hide();
  }

  saveIssue(){
   this.issueService.createIssue(this.issueForm.value).subscribe(
     response =>{
       this.issueForm.reset();
       this.setPage({offset:0});
       this.closeAndResetModel();

     }
   ) 
  }


}
