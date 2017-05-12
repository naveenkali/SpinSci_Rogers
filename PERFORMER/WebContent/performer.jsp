<script type="text/javascript" src="js/pages/indexJS.js"></script>
<h2 class="page-header" id="performerTitle">Performer Of The Release <%=session.getAttribute("releaseDate")%></h2>
					<div class="inner cover" >
					<table>
						<tr>
						<td><p class="form-group">
							<select name="perfomer1" id="perfomer1" class="selectpicker" 
								data-style="btn-primary" onchange="removeFromSelect1();" >
								<option name="ChoosePerformer-1" >ChoosePerformer-1</option>
							</select>
						</p></td><td style="width: 20px">&nbsp;</td><td><label>Justification1 :</label></td>&nbsp;
						<td><p class="form-group ">
							<TEXTAREA NAME="justification1" id="justification1" style="overflow:auto;resize:none" ROWS="2" cols="60" "></TEXTAREA>
						</p></td></tr>
						<tr>
						<td><p class="form-group">
							<select name="perfomer2" id="perfomer2" class="selectpicker" 
								data-style="btn-primary"  onchange="removeFromSelect2();" >
								<option name="ChoosePerformer-2" >ChoosePerformer-2</option>
							</select>
						</p></td><td style="width: 20px">&nbsp;</td><td><label>Justification2 :</label></td>&nbsp;
						<td><p class="form-group ">
							<TEXTAREA NAME="justification2" id="justification2" style="overflow:auto;resize:none"  ROWS="2" cols="60"  ></TEXTAREA>
						</p></td></tr>
						<tr>
						<td><p class="form-group">
							<select name="perfomer3" id="perfomer3" class="selectpicker" 
								data-style="btn-primary" onchange="removeFromSelect3();">
								<option name="ChoosePerformer-3" >ChoosePerformer-3</option>
							</select>
						</p></td><td style="width: 20px">&nbsp;</td><td><label>Justification3 :</label></td>&nbsp;
						<td><p class="form-group ">
							<TEXTAREA NAME="justification3" id="justification3"  style="overflow:auto;resize:none" ROWS="2" cols="60" onchange="ValidatePerformer3();"></TEXTAREA>
						</p></td></tr>
		<tr>
			<td colspan="3" align="center">
				<p class="form-group">
					<button class="btn btn-success" onclick="ValidatePerformer();" />
					Submit
					</button>
				</p>
			</td>
		</tr>
	</table>
</div>
