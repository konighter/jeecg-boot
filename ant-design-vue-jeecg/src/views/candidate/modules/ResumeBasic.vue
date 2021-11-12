<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <!-- 主表单区域 -->
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">

        <!--        新增&编辑界面-->

        <template v-if="editable">



        </template>

        <template v-else >
          <a-space direction="vertical" style="width: 100%;">
            <!--          basic info-->
            <a-card title="基本信息">
              <a slot="extra" href="#"><a-icon type="edit" /></a>
              <a-row type="flex" align="top">
                <a-col :span="2">
                  <a-avatar :size="100" icon="user" src="https://gw.alipayobjects.com/zos/rmsportal/JiqGstEfoWAOHiTxclqi.png" />
                </a-col>
                <a-col :span="19" offset="1">

                  <a-descriptions>
                    <h2 slot="title"> {{model.name}} </h2>
                    <a-descriptions-item label="基本信息"> {{ model.name }} </a-descriptions-item>
                    <a-descriptions-item label="最近工作"> {{ model.name }} </a-descriptions-item>
                    <a-descriptions-item label="最高学历"> {{ model.name }} </a-descriptions-item>
                  </a-descriptions>
                </a-col>
              </a-row>





            </a-card>




            <!--          edu-->
            <a-card title="教育经历">

            </a-card>
          </a-space>

        </template>




      </a-form-model>
    </j-form-container>

  </a-spin>
</template>

<script>

import { getAction } from '@/api/manage'
import { FormTypes,getRefPromise,VALIDATE_NO_PASSED } from '@/utils/JEditableTableUtil'
import { JEditableTableModelMixin } from '@/mixins/JEditableTableModelMixin'
import { validateDuplicateValue } from '@/utils/util'

export default {
  name: 'CandidateFormCard',
  mixins: [JEditableTableModelMixin],
  components: {
  },
  data() {
    return {
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

    editable: {
      type: Boolean,
      default: false,
      required: false
    },
    name: {
      type: String,
      default: '',
      required: false
    }
  },
  computed: {


  },

  created () {

  },
  methods: {


  }
}
</script>

<style scoped>
</style>