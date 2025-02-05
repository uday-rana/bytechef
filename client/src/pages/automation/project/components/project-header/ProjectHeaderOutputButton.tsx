import {Button} from '@/components/ui/button';
import {Tooltip, TooltipContent, TooltipTrigger} from '@/components/ui/tooltip';
import useWorkflowEditorStore from '@/pages/platform/workflow-editor/stores/useWorkflowEditorStore';
import {SquareChevronRightIcon} from 'lucide-react';
import {RefObject} from 'react';
import {ImperativePanelHandle} from 'react-resizable-panels';

const ProjectHeaderOutputButton = ({
    bottomResizablePanelRef,
}: {
    bottomResizablePanelRef: RefObject<ImperativePanelHandle>;
}) => {
    const {setShowBottomPanelOpen, showBottomPanel} = useWorkflowEditorStore();

    return (
        <Tooltip>
            <TooltipTrigger asChild>
                <Button
                    className="hover:bg-surface-neutral-primary-hover [&_svg]:size-5"
                    onClick={() => {
                        setShowBottomPanelOpen(!showBottomPanel);

                        if (bottomResizablePanelRef.current) {
                            bottomResizablePanelRef.current.resize(!showBottomPanel ? 35 : 0);
                        }
                    }}
                    size="icon"
                    variant="ghost"
                >
                    <SquareChevronRightIcon />
                </Button>
            </TooltipTrigger>

            <TooltipContent>Show the current workflow test execution output</TooltipContent>
        </Tooltip>
    );
};

export default ProjectHeaderOutputButton;
