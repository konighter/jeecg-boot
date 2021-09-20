<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <!-- 主表单区域 -->
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="12" >
            <a-form-model-item label="姓名" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="name">
              <a-input v-model="model.name" placeholder="请输入姓名" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12" >
            <a-form-model-item label="工作单位" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="company">
              <a-input v-model="model.company" placeholder="请输入工作单位" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12" >
            <a-form-model-item label="职位" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="job">
              <a-input v-model="model.job" placeholder="请输入职位" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12" >
            <a-form-model-item label="电话号码" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="phoneNum">
              <a-input v-model="model.phoneNum" placeholder="请输入电话号码" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12" >
            <a-form-model-item label="邮箱" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="email">
              <a-input v-model="model.email" placeholder="请输入邮箱" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12" >
            <a-form-model-item label="上次拜访时间" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="lastVisitTime">
              <j-date placeholder="请选择上次拜访时间" v-model="model.lastVisitTime" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="12" >
            <a-form-model-item label="下次拜访时间" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="nextVisitTime">
              <j-date placeholder="请选择下次拜访时间" v-model="model.nextVisitTime" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="标签" :labelCol="labelCol2" :wrapperCol="wrapperCol2" prop="tags">
              <a-textarea v-model="model.tags" rows="4" placeholder="请输入标签" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="自我介绍" :labelCol="labelCol2" :wrapperCol="wrapperCol2" prop="introduction">
              <a-textarea v-model="model.introduction" rows="4" placeholder="请输入自我介绍" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="技能" :labelCol="labelCol2" :wrapperCol="wrapperCol2" prop="skills">
              <a-textarea v-model="model.skills" rows="4" placeholder="请输入技能" />
            </a-form-model-item>
          </a-col>
        </a-row>
      </a-form-model>
    </j-form-container>
      <!-- 子表单区域 -->
    <a-tabs v-model="activeKey" @change="handleChangeTabs">
      <a-tab-pane tab="工作单位" :key="refKeys[0]" :forceRender="true">
        <j-editable-table
          :ref="refKeys[0]"
          :loading="candidateCompanysTable.loading"
          :columns="candidateCompanysTable.columns"
          :dataSource="candidateCompanysTable.dataSource"
          :maxHeight="300"
          :disabled="formDisabled"
          :rowNumber="true"
          :rowSelection="true"
          :actionButton="true"/>
      </a-tab-pane>
      <a-tab-pane tab="候选人简历" :key="refKeys[1]" :forceRender="true">
        <j-editable-table
          :ref="refKeys[1]"
          :loading="candidateResumeTable.loading"
          :columns="candidateResumeTable.columns"
          :dataSource="candidateResumeTable.dataSource"
          :maxHeight="300"
          :disabled="formDisabled"
          :rowNumber="true"
          :rowSelection="true"
          :actionButton="true"/>
      </a-tab-pane>
      <a-tab-pane tab="候选人拜访历史" :key="refKeys[2]" :forceRender="true">
        <j-editable-table
          :ref="refKeys[2]"
          :loading="candidateVisithistoryTable.loading"
          :columns="candidateVisithistoryTable.columns"
          :dataSource="candidateVisithistoryTable.dataSource"
          :maxHeight="300"
          :disabled="formDisabled"
          :rowNumber="true"
          :rowSelection="true"
          :actionButton="true"/>
      </a-tab-pane>
      <a-tab-pane tab="教育经历" :key="refKeys[3]" :forceRender="true">
        <j-editable-table
          :ref="refKeys[3]"
          :loading="candidateEduTable.loading"
          :columns="candidateEduTable.columns"
          :dataSource="candidateEduTable.dataSource"
          :maxHeight="300"
          :disabled="formDisabled"
          :rowNumber="true"
          :rowSelection="true"
          :actionButton="true"/>
      </a-tab-pane>
    </a-tabs>
  </a-spin>
</template>

<script>

  import { getAction } from '@/api/manage'
  import { FormTypes,getRefPromise,VALIDATE_NO_PASSED } from '@/utils/JEditableTableUtil'
  import { JEditableTableModelMixin } from '@/mixins/JEditableTableModelMixin'
  import { validateDuplicateValue } from '@/utils/util'

  export default {
    name: 'CandidateForm',
    mixins: [JEditableTableModelMixin],
    components: {
    },
    data() {
      return {
        labelCol: {
          xs: { span: 24 },
          sm: { span: 6 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        labelCol2: {
          xs: { span: 24 },
          sm: { span: 3 },
        },
        wrapperCol2: {
          xs: { span: 24 },
          sm: { span: 20 },
        },
        model:{
        },
        // 新增时子表默认添加几行空数据
        addDefaultRowNum: 1,
        validatorRules: {
           name: [
              { required: true, message: '请输入姓名!'},
           ],
           company: [
              { required: true, message: '请输入工作单位!'},
           ],
           job: [
              { required: true, message: '请输入职位!'},
           ],
           phoneNum: [
              { required: true, message: '请输入电话号码!'},
              { pattern: /^1[3456789]\d{9}$/, message: '请输入正确的手机号码!'},
           ],
           email: [
              { required: false},
              { pattern: /^([\w]+\.*)([\w]+)@[\w]+\.\w{3}(\.\w{2}|)$/, message: '请输入正确的电子邮件!'},
           ],
        },
        refKeys: ['candidateCompanys', 'candidateResume', 'candidateVisithistory', 'candidateEdu', ],
        tableKeys:['candidateCompanys', 'candidateResume', 'candidateVisithistory', 'candidateEdu', ],
        activeKey: 'candidateCompanys',
        // 工作单位
        candidateCompanysTable: {
          loading: false,
          dataSource: [],
          columns: [
            {
              title: '候选人ID',
              key: 'candidateId',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: '工作单位',
              key: 'company',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: '工作',
              key: 'job',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: '开始时间',
              key: 'startTime',
              type: FormTypes.date,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: '结束时间',
              key: 'endTime',
              type: FormTypes.date,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: '汇报对象',
              key: 'response',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: '团队规模',
              key: 'teams',
              type: FormTypes.inputNumber,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: '下属人数',
              key: 'subordinate',
              type: FormTypes.inputNumber,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: '工作内容',
              key: 'intros',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
          ]
        },
        // 候选人简历
        candidateResumeTable: {
          loading: false,
          dataSource: [],
          columns: [
            {
              title: '简历文本',
              key: 'resumes',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: '简历URL',
              key: 'resumeUrl',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: '简历来源',
              key: 'source',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: '是否最新',
              key: 'current',
              type: FormTypes.inputNumber,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
          ]
        },
        // 候选人拜访历史
        candidateVisithistoryTable: {
          loading: false,
          dataSource: [],
          columns: [
            {
              title: '拜访时间',
              key: 'visitTime',
              type: FormTypes.date,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: '下次拜访时间',
              key: 'nextVisitTime',
              type: FormTypes.date,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: '拜访记录',
              key: 'marks',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
          ]
        },
        // 教育经历
        candidateEduTable: {
          loading: false,
          dataSource: [],
          columns: [
            {
              title: '学校信息',
              key: 'schools',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: '开始时间',
              key: 'startTime',
              type: FormTypes.date,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: '结束时间',
              key: 'endTime',
              type: FormTypes.date,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: '学位',
              key: 'degree',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: '介绍',
              key: 'intros',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
          ]
        },
        url: {
          add: "/candidate/candidate/add",
          edit: "/candidate/candidate/edit",
          queryById: "/candidate/candidate/queryById",
          candidateCompanys: {
            list: '/candidate/candidate/queryCandidateCompanysByMainId'
          },
          candidateResume: {
            list: '/candidate/candidate/queryCandidateResumeByMainId'
          },
          candidateVisithistory: {
            list: '/candidate/candidate/queryCandidateVisithistoryByMainId'
          },
          candidateEdu: {
            list: '/candidate/candidate/queryCandidateEduByMainId'
          },
        }
      }
    },
    props: {
      //表单禁用
      disabled: {
        type: Boolean,
        default: false,
        required: false
      }
    },
    computed: {
      formDisabled(){
        return this.disabled
      },
    },
    created () {
    },
    methods: {
      addBefore(){
        this.candidateCompanysTable.dataSource=[]
        this.candidateResumeTable.dataSource=[]
        this.candidateVisithistoryTable.dataSource=[]
        this.candidateEduTable.dataSource=[]
      },
      getAllTable() {
        let values = this.tableKeys.map(key => getRefPromise(this, key))
        return Promise.all(values)
      },
      /** 调用完edit()方法之后会自动调用此方法 */
      editAfter() {
        this.$nextTick(() => {
        })
        // 加载子表数据
        if (this.model.id) {
          let params = { id: this.model.id }
          this.requestSubTableData(this.url.candidateCompanys.list, params, this.candidateCompanysTable)
          this.requestSubTableData(this.url.candidateResume.list, params, this.candidateResumeTable)
          this.requestSubTableData(this.url.candidateVisithistory.list, params, this.candidateVisithistoryTable)
          this.requestSubTableData(this.url.candidateEdu.list, params, this.candidateEduTable)
        }
      },
      //校验所有一对一子表表单
      validateSubForm(allValues){
          return new Promise((resolve,reject)=>{
            Promise.all([
            ]).then(() => {
              resolve(allValues)
            }).catch(e => {
              if (e.error === VALIDATE_NO_PASSED) {
                // 如果有未通过表单验证的子表，就自动跳转到它所在的tab
                this.activeKey = e.index == null ? this.activeKey : this.refKeys[e.index]
              } else {
                console.error(e)
              }
            })
          })
      },
      /** 整理成formData */
      classifyIntoFormData(allValues) {
        let main = Object.assign(this.model, allValues.formValue)
        return {
          ...main, // 展开
          candidateCompanysList: allValues.tablesValue[0].values,
          candidateResumeList: allValues.tablesValue[1].values,
          candidateVisithistoryList: allValues.tablesValue[2].values,
          candidateEduList: allValues.tablesValue[3].values,
        }
      },
      validateError(msg){
        this.$message.error(msg)
      },

    }
  }
</script>

<style scoped>
</style>